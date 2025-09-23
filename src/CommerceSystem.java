import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    List<Category> categories =  new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public CommerceSystem() {
        Category ele = new Category("전자제품");
        ele.products.add(new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 20));
        ele.products.add(new Product("iPhone 16","1,350,000원", "Apple의 최신 스마트폰", 50));
        ele.products.add(new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 20));
        ele.products.add(new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 5));
        categories.add(ele);

        Category foods = new Category("식품");
        foods.products.add(new Product("사과", "1,200원", "유기농 사과", 10));
        foods.products.add(new Product("바나나", "2,000원", "필리핀 바나나", 3));
        categories.add(foods);

        Category clothes = new Category("의류");
        clothes.products.add(new Product("디자인티셔츠", "20,000원", "토마토그림이있는 티셔츠", 10));
        categories.add(clothes);
    }

    void start(){
        int i = 0;
        for  (Category c : categories) {
            System.out.println((i+1)+". " + c.getName());
            i++;
        }

        System.out.println("0. 종료   |   프로그램 종료");
        String next = sc.nextLine();

        if ("1".equals(next)) {
            Category c1 = categories.get(0);
            i=0;
            for(Product p : c1.products) {
                System.out.println((i+1)+". " + p);
                i++;
            }
        }else if ("2".equals(next)) {
            Category c2 = categories.get(1);
            i=0;
            for(Product p : c2.products) {
                System.out.println((i+1)+". " + p);
                i++;
            }
        }else if ("3".equals(next)) {
            Category c3 = categories.get(2);
            i=0;
            for(Product p : c3.products) {
                System.out.println((i+1)+". " + p);
                i++;
            }
        }


        if(next.equals("0")) {
            System.out.println("커머스 플랫폼을 종료합니다.");
            sc.close();
            System.exit(0);
        }
    }
}
