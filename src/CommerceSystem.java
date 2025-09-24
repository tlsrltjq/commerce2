import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private List<Category> categories = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public CommerceSystem() {
        Category ele = new Category("전자제품");
        Category foods = new Category("식품");
        Category clothes = new Category("의류");

        ele.getProducts().add(new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 20));
        ele.getProducts().add(new Product("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 50));
        ele.getProducts().add(new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 20));
        ele.getProducts().add(new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 5));
        foods.getProducts().add(new Product("사과", "1,200원", "유기농 사과", 10));
        foods.getProducts().add(new Product("바나나", "2,000원", "필리핀 바나나", 3));
        clothes.getProducts().add(new Product("디자인티셔츠", "20,000원", "토마토그림이있는 티셔츠", 10));

        categories.add(ele);
        categories.add(foods);
        categories.add(clothes);
    }

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
            int pNum = Integer.parseInt(next);

            if (next.equals("0")) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                sc.close();
                System.exit(0);
            } else {
                switch (pNum) {
                    case 1:
                        categoryList(pNum - 1);
                        int pNum2 = numberInput();

                        if (pNum2 == 0) continue;
                        else productList(categories.get(pNum - 1), pNum2);

                        break;
                    case 2:
                        categoryList(pNum - 1);
                        pNum2 = numberInput();

                        if (pNum2 == 0) continue;
                        else productList(categories.get(pNum - 1), pNum2);

                        break;
                    case 3:
                        categoryList(pNum - 1);
                        pNum2 = numberInput();

                        if (pNum2 == 0) continue;
                        else productList(categories.get(pNum - 1), pNum2);

                        break;
                    default:
                        System.out.println("보기에 있는 숫자 중에 눌러주세요");
                }
            }
        }
    }

    void categoryList(int i) {
        System.out.println("[ " + categories.get(i).categoryName + " 카테고리 ]");
        int j = 1;
        for (Product p : categories.get(i).getProducts()) {
            System.out.println(j + ". " + p);
            j++;
        }
        System.out.println("0. 뒤로가기");
    }

    void productList(Category c, int pNum2) {
        pNum2 -= 1;
        Product select = c.getProducts().get(pNum2);
        System.out.println("선택한 상품 : " + select + " | 남은 재고: " + select.getStock() + "개");
        System.out.println("");
    }

    int numberInput() {
        int pNum2 = sc.nextInt();
        sc.nextLine();

        return pNum2;
    }
}
