import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private List<Category> categories = new ArrayList<>();
    private List<CartItem> cartItems = new ArrayList<>();
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
        clothes.getProducts().add(new Product("멋진 바지", "50,000원", "트렌디한 바지", 0));

        categories.add(ele);
        categories.add(foods);
        categories.add(clothes);
    }

    public void start() {
        while (true) {
            commerceMainMenu();
        }
    }

    public void productList(int index) {
        System.out.println("\n[ " + categories.get(index).getName() + " 카테고리 ]");
        int j = 1;
        for (Product product : categories.get(index).getProducts()) {
            System.out.println(j + ". " + product);
            j++;
        }
        System.out.println("0. 뒤로가기");
    }

    public void selectProduct(Category category, int i) {
        int index = i - 1;
        int listSize = category.getProducts().size();

        if (index < 0 || index >= listSize) {
            System.err.println("잘못된 상품 번호입니다. " + category.getName() + " 카테고리에는 " + listSize + "개의 상품이 있습니다.");
            return;
        }
        Product select = category.getProducts().get(index);
        System.out.println("선택한 상품 : " + select + " | 남은 재고: " + select.getStock() + "개\n");
        confirmAddToCart(select);
    }

    public int numInput() {
        try {
            System.out.print(":");
            int pNum = sc.nextInt();
            sc.nextLine();
            return pNum;
        } catch (InputMismatchException e) {
            System.err.println("숫자만 입력해야 합니다. 잘못된 입력입니다.");
            sc.nextLine();
            return numInput();
        }
    }

    public boolean confirmAddToCart(Product product) {
        while (true) {
            System.out.println(product);
            System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
            System.out.println("1. 확인       2. 취소");
            System.out.print(":");
            try {
                int pNum3 = sc.nextInt();
                sc.nextLine();
                if (pNum3 == 1) {
                    cartItems.add(new CartItem(product, 1));
                    System.out.println(product.getName() + "이(가) 장바구니에 추가되었습니다.");
                    return true;
                } else if (pNum3 == 2) {
                    return false;
                } else {
                    System.out.println("보기에 숫자를 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.err.println("숫자만 입력해야 합니다. 잘못된 입력입니다.");
                sc.nextLine();
                return confirmAddToCart(product);
            }
        }
    }

    public void cartItemList() {
        System.out.println("[ 장바구니 내역 ]");
        int i = 1;
        for (CartItem item : cartItems) {
            System.out.println(i + ". " + item);
            i++;
        }
    }

    public void orderManagement() {
        System.out.println("\n[ 주문 관리 ]");
        System.out.println("4. 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
        System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다(장바구니 초기화)");
    }

    public void commerceMainMenu() {
        int i = 0;
        System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
        for (Category category : categories) {
            System.out.println((i + 1) + ". " + category.getName());
            i++;
        }
        System.out.println("0. 종료   |   프로그램 종료");

        if (cartItems.size() > 0) {
            orderManagement();
        }

        int pNum = numInput();
        if (pNum == 0) {
            System.out.println("커머스 플랫폼을 종료합니다.");
            sc.close();
            System.exit(0);
        } else {
            switch (pNum) {
                case 1, 2, 3:
                    int index = pNum - 1;
                    productList(index);
                    int pNum2 = numInput();

                    if (pNum2 == 0) commerceMainMenu();
                    else selectProduct(categories.get(index), pNum2);
                    break;
                case 4:
                    if (cartItems.isEmpty()) {
                        System.out.println("보기에 있는 숫자 중에 눌러주세요");
                        break;
                    }
                    order();
                    break;
                case 5:
                    if (cartItems.isEmpty()) {
                        System.out.println("보기에 있는 숫자 중에 눌러주세요");
                        break;
                    }
                    cartItems.clear();
                    break;
                default:
                    System.out.println("보기에 있는 숫자 중에 눌러주세요");
            }//switch
        }//else
    }

    public long total() {
        long totalPrice = 0;
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            String clearPrice = product.getPrice().replaceAll("[^0-9]", "");
            long price = Long.parseLong(clearPrice);
            totalPrice += price * quantity;
        }
        return totalPrice;
    }

    public void setStock() {
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            int stock = product.getStock() - quantity;
            System.out.println(product.getName() + " 재고가 " + product.getStock() + "개 → " + stock + "개로 업데이트되었습니다.");
            product.setStock(stock);
        }
    }

    public void order() {
        while (true) {
            System.out.println("아래와 같이 주문 하시겠습니까?");
            cartItemList();

            System.out.println("\n[ 총 주문 금액 ]");
            long total = total();
            System.out.println(total + "원");

            System.out.println("\n1. 주문 확정      2. 메인으로 돌아가기");
            int num = numInput();
            if (num == 1) {
                System.out.println("주문이 완료되었습니다! 총 금액: " + total + "원");
                setStock();
                cartItems.clear();
                return;
            } else if (num == 2) {
                System.out.println("메인으로 돌아갑니다.");
                return;
            } else {
                System.out.println("보기에 있는 숫자 중에 눌러주세요");
            }
        }//while
    }
}//CommerceSystem
