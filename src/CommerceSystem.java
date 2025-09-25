import java.util.ArrayList;
import java.util.InputMismatchException;
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
        clothes.getProducts().add(new Product("디자인티셔츠", "20,000원", "토마토 그림이 있는 티셔츠", 10));

        categories.add(ele);
        categories.add(foods);
        categories.add(clothes);
    }

    public void start() {
        while (true) {
            int i = 0;
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            for (Category c : categories) {
                System.out.println((i + 1) + ". " + c.getName());
                i++;
            }
            System.out.println("0. 종료   |   프로그램 종료");

            int pNum = firstInput();
            if (pNum == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                sc.close();
                System.exit(0);
            } else {
                switch (pNum) {
                    case 1:
                        int index = pNum - 1;
                        productList(index);
                        int pNum2 = secondInput();

                        if (pNum2 == 0) continue;
                        else selectProduct(categories.get(index), pNum2);
                        break;

                    case 2, 3:
                        index = pNum - 1;
                        productList(index);
                        pNum2 = secondInput();

                        if (pNum2 == 0) continue;
                        else selectProduct(categories.get(index), pNum2);
                        break;

                    default:
                        System.out.println("보기에 있는 숫자 중에 눌러주세요");
                }
            }
        }
    }

    public void productList(int i) {
        System.out.println("[ " + categories.get(i).categoryName + " 카테고리 ]");
        int j = 1;
        for (Product p : categories.get(i).getProducts()) {
            System.out.println(j + ". " + p);
            j++;
        }
        System.out.println("0. 뒤로가기");
    }

    public void selectProduct(Category category, int pNum2) {
        int index = pNum2 - 1;
        int listSize = category.getProducts().size();

        if (index < 0 || index >= listSize) {
            System.err.println("잘못된 상품 번호입니다. " + category.getName() + " 카테고리에는 " + listSize + "개의 상품이 있습니다.");
            return;
        }
        Product select = category.getProducts().get(index);
        System.out.println("선택한 상품 : " + select + " | 남은 재고: " + select.getStock() + "개");
        System.out.println("");
    }

    public int firstInput() {
        try {
            int pNum = sc.nextInt();
            sc.nextLine();
            return pNum;
        } catch (InputMismatchException e) {
            System.err.println("숫자만 입력해야 합니다. 잘못된 입력입니다.");
            sc.nextLine();
            return firstInput();
        }
    }

    public int secondInput() {
        while (true) {
            try {
                int pNum2 = sc.nextInt();
                sc.nextLine();
                return pNum2;
            } catch (InputMismatchException e) {
                System.err.println("숫자만 입력해야 합니다. 잘못된 입력입니다.");
                sc.nextLine();
                return secondInput();
            }
        }
    }
}
