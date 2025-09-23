import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    List<Category> categories = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Category ele;
    Category foods;
    Category clothes;
    

    public CommerceSystem() {
        ele = new Category("전자제품");
        ele.products.add(new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 20));
        ele.products.add(new Product("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 50));
        ele.products.add(new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 20));
        ele.products.add(new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 5));
        categories.add(ele);

        foods = new Category("식품");
        foods.products.add(new Product("사과", "1,200원", "유기농 사과", 10));
        foods.products.add(new Product("바나나", "2,000원", "필리핀 바나나", 3));
        categories.add(foods);

        clothes = new Category("의류");
        clothes.products.add(new Product("디자인티셔츠", "20,000원", "토마토그림이있는 티셔츠", 10));
        categories.add(clothes);
    }

    int pNum;

    void start() {
        while (true) {
            int i = 0;
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            for (Category c : categories) {
                System.out.println((i + 1) + ". " + c.getName());
                i++;
            }

            System.out.println("0. 종료   |   프로그램 종료");
            String next = sc.nextLine();


            if (next.equals("0")) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                sc.close();
                System.exit(0);
            } else {
                switch (next) {
                    case "1":
                        System.out.println("[ " + ele.categoryName + " 카테고리 ]");
                        ele = categories.get(0);
                        i = 0;
                        for (Product p : ele.products) {
                            System.out.println((i + 1) + ". " + p);
                            i++;
                        }
                        System.out.println("0. 뒤로가기");

                        pNum = sc.nextInt();
                        sc.nextLine();
                        if (pNum == 0) {
                            continue;
                        } else {
                            categoryList(ele);
                        }

                        break;


                    case "2":
                        System.out.println("[ " + foods.categoryName + " 카테고리 ]");
                        Category c2 = categories.get(1);
                        i = 0;
                        for (Product p : c2.products) {
                            System.out.println((i + 1) + ". " + p);
                            i++;
                        }
                        System.out.println("0. 뒤로가기");
                        categoryList(foods);

                        pNum = sc.nextInt();
                        sc.nextLine();
                        if (pNum == 0) {
                            continue;
                        } else {
                            categoryList(foods);
                        }

                        break;

                    case "3":
                        System.out.println("[ " + clothes.categoryName + " 카테고리 ]");
                        Category c3 = categories.get(2);
                        i = 0;
                        for (Product p : c3.products) {
                            System.out.println((i + 1) + ". " + p);
                            i++;
                        }
                        System.out.println("0. 뒤로가기");
                        categoryList(clothes);

                        pNum = sc.nextInt();
                        sc.nextLine();
                        if (pNum == 0) {
                            continue;
                        } else {
                            categoryList(clothes);
                        }

                        break;

                    default:
                        System.out.println("보기에 있는 숫자 중에 눌러주세요");
                }

            }


        }

    }

    void categoryList(Category c) {

        pNum -= 1;
        Product select = c.products.get(pNum);
        System.out.println("선택한 상품 : " + select + " | 남은 재고: " + select.stock + "개");
        System.out.println("");
    }
}
