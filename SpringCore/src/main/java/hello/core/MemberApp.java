//package hello.core;
//
//import hello.core.member.Grade;
//import hello.core.member.Member;
//import hello.core.member.MemberService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class MemberApp {
//    public static void main(String[] args) {
//
////        AppConfig appConfig = new AppConfig();
////        MemberService memberService = appConfig.memberService();
//
//        // 객체 타입은 ApplicationContext : 애플리케이션 컨테이너, 인스턴스는 AnnotationConfigApplicationContext(Config 클래스)
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        // 첫번째 매개변수 : config 클래스에 있는 메서드 이름, 두번째 매개변수 : 반환타입
//        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
//
//
//
//
//        Member member = new Member(1L, "memberA", Grade.VIP);
//        memberService.join(member);
//
//        Member findMember = memberService.findMember(1L);
//        System.out.println("new member = " + member.getName());
//        System.out.println("find member = " + findMember.getName());
//    }
//}
