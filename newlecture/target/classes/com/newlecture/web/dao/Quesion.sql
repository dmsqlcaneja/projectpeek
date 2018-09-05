-- select top 5 * from Member
--select * from member
--order by regDate desc;


insert into Member(id, name, email, pwd)
      values ('hoon', '남지훈', 'hoon@gmail.com', '$2a$10$zpE1ThBwaRlZM2uMMShksurhrRjw/QtUZXB4cfON4.owLTFqkyQx.');

-- select * from member;

insert into MemberRole(memberId,roleName) values('hoon','ROLE_ADMIN');

-- select * from MemberRole


-- update Member set id='koby', name='아침' from Member where id = 'test1'

select * from MemberRole;
update MemberRole set defaultRole=1
where member Id='hoon' ;
