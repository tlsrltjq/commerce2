import java.text.DecimalFormat;
import java.util.*;

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

    //커머스 시스템 시작
    public void start() {
        while (true) {
            commerceMainMenu();
        }
    }

    //카테고리 인덱스 받아서 제품 리스트 출력
    public void productList(int index) {
        System.out.println("\n[ " + categories.get(index).getName() + " 카테고리 ]");
        int j = 1;
        for (Product product : categories.get(index).getProducts()) {
            System.out.println(j + ". " + product);
            j++;
        }
        System.out.println("0. 뒤로가기");
    }

    //상품 선택
    public void selectProduct(Category category, int i) {
        int index = i - 1;
        int listSize = category.getProducts().size();

        if (index < 0 || index >= listSize) {
            System.err.println("잘못된 상품 번호입니다. " + category.getName() + " 카테고리에는 " + listSize + "개의 상품이 있습니다.");
            return;
        }
        Product select = category.getProducts().get(index);

        if (select.getStock() > 0) {
            System.out.println("선택한 상품 : " + select + " | 남은 재고: " + select.getStock() + "개\n");
            confirmAddToCart(select);
        } else {
            System.out.println("선택한 상품 : " + select.getName() + "의 재고가 남지않았습니다.");
        }
    }

    //숫자 입력 받기
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

    //장바구니에 추가
    public boolean confirmAddToCart(Product product) {
        while (true) {
            System.out.println(product);
            System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
            System.out.println("1. 확인       2. 취소");
            System.out.print(":");

            int i = numInput();
            if (i == 1) {
                cartItems.add(new CartItem(product, 1));
                System.out.println(product.getName() + "이(가) 장바구니에 추가되었습니다.");
                return true;
            } else if (i == 2) {
                return false;
            } else {
                System.out.println("보기에 숫자를 입력해주세요.");
            }
        }//while
    }


    //장바구니 리스트 출력
    public void cartItemList() {
        System.out.println("[ 장바구니 내역 ]");
        int i = 1;
        for (CartItem item : cartItems) {
            System.out.println(i + ". " + item);
            i++;
        }
    }

    //주문관리 텍스트
    public void orderManagement() {
        System.out.println("\n[ 주문 관리 ]");
        System.out.println("4. 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
        System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다(장바구니 초기화)");
    }

    //커머스 시스템 메인
    public void commerceMainMenu() {
        int i = 0;
        System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
        for (Category category : categories) {
            System.out.println((i + 1) + ". " + category.getName());
            i++;
        }
        System.out.println("0. 종료      | 프로그램 종료");
        System.out.println("6. 관리자 모드");
        if (cartItems.size() > 0) {
            orderManagement();
        } //장바구니 리스트에 값이 있으면 주문관리 출력

        int pNum = numInput();
        if (pNum == 0) {
            System.out.println("커머스 플랫폼을 종료합니다.");
            sc.close();
            System.exit(0);
        } else {
            switch (pNum) {
                case 1, 2, 3:   //카테고리 선택
                    int index = pNum - 1;
                    productList(index);
                    int pNum2 = numInput();
                    if (pNum2 == 0) commerceMainMenu();
                    else selectProduct(categories.get(index), pNum2);
                    break;
                case 4:     //주문관리 - 장바구니 확인
                    if (cartItems.isEmpty()) {
                        System.out.println("보기에 있는 숫자 중에 눌러주세요");
                        break;
                    }
                    order();
                    break;
                case 5:     //주문관리 - 주문취소
                    if (cartItems.isEmpty()) {
                        System.out.println("보기에 있는 숫자 중에 눌러주세요");
                        break;
                    }
                    cartItems.clear();
                    break;
                case 6:     //관리자 모드
                    System.out.println("관리자 비밀번호를 입력해주세요:");
                    for (int k = 3; k > 0; k--) {
                        String pw = sc.nextLine();
                        if (pw.equals("admin123")) {
                            adminMode();
                            break;
                        } else {
                            System.out.println("비밀번호가 틀렸습니다. 다시 입력해주세요. " + (k - 1) + "번 남음");
                        }
                    }
                    break;
                default:
                    System.out.println("보기에 있는 숫자 중에 눌러주세요");
            }//switch
        }//else
    }

    //장바구니에 있는 제품 총합 가격 구하기
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

    //재고 차감
    public void setStock() {
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            int stock = product.getStock() - quantity;
            System.out.println(product.getName() + " 재고가 " + product.getStock() + "개 → " + stock + "개로 업데이트되었습니다.");
            product.setStock(stock);
        }
    }

    //주문 확인 및 확정
    public void order() {
        while (true) {
            System.out.println("아래와 같이 주문 하시겠습니까?");
            cartItemList();

            System.out.println("\n[ 총 주문 금액 ]");
            long total = total();
            String price = transPrice(total);
            System.out.println(price);

            System.out.println("\n1. 주문 확정      2. 메인으로 돌아가기");
            int num = numInput();
            if (num == 1) {
                System.out.println("주문이 완료되었습니다! 총 금액: " + price);
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

    //관리자 모드
    public void adminMode() {
        System.out.println("[ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");

        int i = numInput();
        int j = 0;
        switch (i) {
            case 0:
                break;
            case 1:     //상품 추가
                System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
                for (Category category : categories) {
                    System.out.println((j + 1) + ". " + category.getName());
                    j++;
                }
                int index = numInput() - 1;

                System.out.println("[ " + categories.get(index).getName() + " 카테고리에 상품 추가 ]");
                String name, price, exp;
                System.out.print("상품명을 입력해주세요: ");
                name = sc.nextLine();
                System.out.print("가격을 입력해주세요: ");
                price = sc.nextLine();
                price = transPrice(price);
                System.out.print("상품 설명을 입력해주세요: ");
                exp = sc.nextLine();
                System.out.print("재고수량을 입력해주세요: ");
                int stock = numInput();

                while (true) {
                    System.out.println(name + " | " + price + " | " + exp + " | 재고: " + stock + "개");
                    System.out.println("위 정보로 상품을 추가하시겠습니까?");
                    System.out.println("1. 확인    2. 취소");
                    i = numInput();
                    if (i == 1) {
                        System.out.println("상품이 성공적으로 추가되었습니다!");
                        categories.get(index).getProducts().add(new Product(name, price, exp, stock));
                        adminMode();
                        return;
                    } else if (i == 2) {
                        System.out.println("상품 등록을 취소합니다.");
                        adminMode();
                        return;
                    } else {
                        System.out.println("보기에 있는 숫자 중에 눌러주세요");
                    }
                }

            case 2:     //상품 수정
                System.out.print("수정할 상품명을 입력해주세요: ");
                String updateName = sc.nextLine();

                for (Category category : categories) {
                    for (Product product : category.getProducts()) {
                        if (updateName.equals(product.getName())) {
                            System.out.println("현재 상품 정보: " + product + " | 재고: " + product.getStock() + "개");
                            System.out.println("\n수정할 항목을 선택해주세요:");
                            System.out.println("1. 가격");
                            System.out.println("2. 설명");
                            System.out.println("3. 재고수량");
                            while (true) {
                                int k = numInput();
                                switch (k) {
                                    case 1:
                                        System.out.println("현재 가격: " + product.getPrice());
                                        System.out.print("새로운 가격을 입력해주세요: ");
                                        String newPrice = sc.nextLine();
                                        System.out.print(product.getName() + "의 가격이 " + product.getPrice());
                                        newPrice = transPrice(newPrice);
                                        product.setPrice(newPrice);
                                        System.out.println(" → " + product.getPrice() + "으로 수정되었습니다.");
                                        adminMode();
                                        return;
                                    case 2:
                                        System.out.println("현재 설명: " + product.getExp());
                                        System.out.println("새로운 설명을 입력해주세요: ");
                                        String newExp = sc.nextLine();
                                        System.out.println();
                                        System.out.print(product.getName() + "의 설명이 '" + product.getExp());
                                        product.setExp(newExp);
                                        System.out.println("' → '" + product.getExp() + "'으로 수정되었습니다.");
                                        adminMode();
                                        return;
                                    case 3:
                                        System.out.println("현재 재고: " + product.getStock() + "개");
                                        System.out.println("재고를 설정해주세요: ");
                                        int newStock = numInput();
                                        System.out.println();
                                        System.out.print(product.getName() + "의 재고가 " + product.getStock());
                                        product.setStock(newStock);
                                        System.out.println(" → " + product.getStock() + "으로 수정되었습니다.");
                                        adminMode();
                                        return;
                                    default:
                                        System.out.println("보기에 있는 숫자 중에 눌러주세요");

                                }//switch
                            }//while
                        } //If
                    }//for
                }//for
                System.out.println("입력한 제품 '" + updateName + "'을 찾을 수 없습니다.");
                adminMode();
                break;

            case 3:     //상품 삭제
                System.out.print("삭제할 상품명을 입력해주세요: ");
                String delName = sc.nextLine();

                for (Category category : categories) {
                    if (category.getProducts().removeIf(product -> product.getName().equals(delName))) {
                        System.out.println(delName + " 상품이 삭제되었습니다." + category.getName());
                        adminMode();
                        return;
                    }
                }
                System.out.println("해당 상품을 찾을 수 없습니다.");
                adminMode();

            case 4:     //모든 카테고리 + 제품 리스트 출력
                for (Category category : categories) {
                    System.out.println("[ " + category.getName() + " 카테고리 ]");
                    for (Product product : category.getProducts()) {
                        System.out.println(product + " | 재고: " + product.getStock());
                    }
                }
                adminMode();
                return;
            default:
                System.out.println("보기에 있는 숫자 중에 눌러주세요");
                adminMode();
        }//switch
    }


    //숫자 변환기 : 세자리마다 ',' 붙여줌
    public String transPrice(Object value) {
        DecimalFormat df = new DecimalFormat("#,###");
        if (value instanceof Integer) {
            return df.format((Integer) value) + "원";
        } else if (value instanceof Long) {
            return df.format((Long) value) + "원";
        } else if (value instanceof String) {
            String clean = ((String) value).replaceAll("[^0-9-]", "");
            if (clean.isEmpty() || clean.equals("-")) return "";
            long num = Long.parseLong(clean);
            return df.format(num) + "원";
        }
        return "";
    }
}//CommerceSystem
