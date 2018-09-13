package com.newlecture.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.PageAttributes.MediaType;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.asm.ByteVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.mybatis.MybatisMemberDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MybatisHomeService;
import com.sun.mail.iap.Response;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MybatisHomeService service; 
	//service의 정의 
	
//	@Autowired
//	private MemberDao memberDao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("join")
	public String join(Model model) {
//		Member member = memberDao.get("newlect");
//		model.addAttribute("member", member);
		return "member.join";
	}
	
	@GetMapping("join-email")
	public String joinemail() {
		
		return "member.join-email";
	
	}
	
	@GetMapping("email-duplicated-error")
	@ResponseBody
	public String emailDuplicatedError() {
		
		return "<script>alert('이미 가입된 이메일 입니다. ');location.href='join-email';</script>";
		
	
	}
	
	@GetMapping("join-invalide-error")
	@ResponseBody
	public String joinInvalideError() {
		
		return "<script>alert('계산식이 올바르지 않습니다.');location.href='join-email';</script>";
		
	
	}
	
	
	//2018.09.10 월요일 
	@GetMapping("is-id-duplicated")
	@ResponseBody
	//@ResponseBody 는 주소를 찾지 않게 해주는 어노테이션 
	public String isIdDuplicated(String id) {
		
		boolean duplicated = service.isIdDuplicated(id);
		
		if(duplicated)
			return "ture";
	
		return "false";
	
	
	}	
	
	//2018.09.10 월요일 
	@PostMapping("join-email")
	public String joinemail(String email, HttpServletResponse response) {
		
		//이 주소 중복조회해봐->(있습니다./없습니다.)/이 주소 있어?/이 주소 유효해?
		boolean duplicated = service.isEmailDuplicated(email);
		
		
		//이메일 중복에대한 에러메세지를 보내자!!
		if(duplicated)
			return"redirect:email-duplicated-error";
		
		/*----------------------------------------------------------*/
		UUID uuid = UUID.randomUUID();
		MessageDigest salt = null;
		String digest = null;
		
		
		try {
			salt = MessageDigest.getInstance("SHA-256");
			salt.update(uuid.toString().getBytes());
				
			byte[] key = salt.digest();
			//바이트열을 문자열로 바꾸기위해서 더하기가 반복되어야 한다. 
			StringBuilder builder = new StringBuilder();
			for(byte b : key)
				builder.append(String.format("%02x", b));
				
			digest = builder.toString();
				
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(uuid);
		System.out.println(digest);
		System.out.println(email);
		
		Cookie cookie = new Cookie("joinId", digest);
		cookie.setPath("/member/"); // 경로는 무슨 용도? 
		cookie.setMaxAge(60*60*24); // 단위!? 
		
		response.addCookie(cookie); 
		
		MimeMessage message =  mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 회원가입을 위한 인증메일");
			helper.setText("<a href=\"http://localhost:8080/member/join-reg?id="+digest+"&em="+email+"\">가입링크</a>", true);
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		mailSender.send(message);
		
		return "member.join-email-info";
	
	}
	
	@GetMapping("join-reg")
	public String joinreg(@RequestParam("id") String key
			, @RequestParam(value="em", defaultValue="") String email
			, @CookieValue(value="joinId", defaultValue="") String joinId
			,Model model) {
	
		
		if(key.equals("") || joinId.equals("") || !key.equals(joinId))
			return "redirect:join-error";
		
		String uid = email.split("@")[0]; // newlec@naver.com 에서 앞에 newlec 만 발췌하는 코드 
		
		model.addAttribute("uid", uid);
		model.addAttribute("email", email);
		
		return "member.join-reg";
	
	}
	
	//포스트를 처리할 수 있는 컨트롤러 
	@PostMapping("join-reg")
	public String joinreg(Member member, 
						  @RequestParam("photo-file") MultipartFile photoFile,
						  Integer moonjae,
						  
						  HttpServletRequest request) throws IOException {
		
		HttpSession session = request.getSession();
		Integer moonjaeSaved = (Integer) session.getAttribute("moonjae");
		
		if(moonjae != moonjaeSaved)
			return "member.join-error";
			//문제 유효성 검사 
			
			
		String resLocation = "/resources/users/newlec/";
		
		ServletContext context = request.getServletContext();
		String homeDr = context.getRealPath(resLocation);
		String uploadedFileName = photoFile.getOriginalFilename();
		//윈도우와 유닉스의 구분자 확인법 File.separator
		String filePath = homeDr + uploadedFileName;
		
		System.out.println(filePath);
		
		File dir = new File(homeDr);
		if(!dir.exists())
			dir.mkdirs();
		
		InputStream fis =  photoFile.getInputStream(); 
		FileOutputStream fos =  new FileOutputStream(filePath);
		
		
		//fis에서 읽어서 fos 으로 복사하기 
		int size = 0;
		byte [] buffer = new byte[1024];
		
        while((size = fis.read(buffer, 0, buffer.length)) > 0) {
        	fos.write(buffer, 0, size);
        }
        
        fis.close();
        fos.close();
        
        System.out.println("파일이 복사되었습니다.");
        
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode(member.getPwd());
        
        member.setPhoto(uploadedFileName);
        member.setPwd(encodedPwd);
        
        System.out.println(encodedPwd);
        
        service.insertMember(member);
        

		return "redirect:../index";
	
	}
	
	@GetMapping("moonjae.jpg")
	public void moonjae(HttpSession session, HttpServletResponse response) throws IOException {
		
		Random random = new Random();
		
		int x = random.nextInt(100);
		int y = random.nextInt(10);
		
		String fmtString = String.format("%d+%d", x, y);
		
		//손 봐야함 
		session.setAttribute("moonjae", x+y);
		
		BufferedImage img = new BufferedImage(60, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics(); 
		g.setFont(new Font("돋움", 0, 13));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 60, 30);
		
		g.setColor(Color.BLACK); 
		g.drawString(fmtString, 5, 20);
		
		response.setContentType("image/png");
		ImageIO.write(img, "png", response.getOutputStream());
		
	
	}
	
	@GetMapping("login")
	public String login() {
		return "member.login";
	}	
	
	@GetMapping("reset-pwd")
	public String resetPwd(String id) {
		
		Member member = service.getMember(id);
		String email = member.getEmail();
		
		//---- 메일 키 확인  ------------------------------------------------------------
		UUID uuid = UUID.randomUUID();
		MessageDigest salt = null;
		String digest = null;
		
		
		try {
			salt = MessageDigest.getInstance("SHA-256");
			salt.update(uuid.toString().getBytes());
				
			byte[] key = salt.digest();
			//바이트열을 문자열로 바꾸기위해서 더하기가 반복되어야 한다. 
			StringBuilder builder = new StringBuilder();
			for(byte b : key)
				builder.append(String.format("%02x", b));
				
			digest = builder.toString();
				
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//---- 메일 보내기 ------------------------------------------------------------
		
		MimeMessage message =  mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 비밀번호 재설정을 위한 인증메일");
			helper.setText("<a href=\"http://localhost:8080/member/join-reg?id="+digest+"&em="+email+"\">가입링크</a>", true);
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		mailSender.send(message);
		
		
		return "member.reset-pwd";
	}	
	
	// 네이버 로그인 ----------------------------------------------------------------
	
	@RequestMapping(value = "naverLogin")
	public String naverLogin(HttpServletRequest request) throws Exception  {
		return "member.naver-login";
	}
	
	@RequestMapping(value = "callback")
	public String navLogin(HttpServletRequest request) throws Exception  {
		return "member.callback";
	}	
	
	@RequestMapping(value = "personalInfo")
	public void personalInfo(HttpServletRequest request) throws Exception {
	        String token = "AAAAONuDkkmfdZPyV1y76tOQCuSnMok9Qmjv+6lLhgxqgPLv/FO+8oJ9d7WgeYWv+xmHKilOXj5z6oFr7cPFLRbUq38=";// 네이버 로그인 접근 토큰; 여기에 복사한 토큰값을 넣어줍니다.
	        String header = "Bearer " + token; // Bearer 다음에 공백 추가
	        try {
	            String apiURL = "https://openapi.naver.com/v1/nid/me";
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            con.setRequestProperty("Authorization", header);
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            System.out.println(response.toString());
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	}

}
