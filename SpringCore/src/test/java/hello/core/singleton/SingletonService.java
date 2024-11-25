package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자를 통해 외부에서 새로 객체를 생성하지 못하도록 막음
    private SingletonService() {
    }

    public void logic(){
        System.out.println("싱글톤 로직 호출");
    }

}
