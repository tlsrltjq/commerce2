import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    List<Product> products = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    CommerceSystem() {
        products.add(new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 20));
        products.add(new Product("iPhone 16","1,350,000원", "Apple의 최신 스마트폰", 50));
        products.add(new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 20));
        products.add(new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 5));

    }

    void start(){
        int i = 0;
        for  (Product p : products) {
            System.out.println((i+1)+". " + p);
            i++;
        }
        System.out.println("0. 종료   |   프로그램 종료");
        String next = sc.nextLine();
        if(next.equals("0")) {
            System.out.println("커머스 플랫폼을 종료합니다.");
            sc.close();
            System.exit(0);
        }
    }
}
