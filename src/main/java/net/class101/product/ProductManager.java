package net.class101.product;

import net.class101.etc.MessageManager;
import net.class101.product.abs.Product;
import net.class101.product.abs.ProductStock;

import java.util.HashMap;
import java.util.Set;

/**
 * 상품관리 클래스
 */
public class ProductManager {

    private static class holder {
        public static final ProductManager INSTANCE = new ProductManager();
    }

    public static ProductManager getInstance() {
        return holder.INSTANCE;
    }


    // 상품목록 저장
    private HashMap<Long, Product> products = ProductInit.created();

    public Set<Long> keySet() {
        return products.keySet();
    }

    public Product get(Long productNo) {
        checkNull(productNo);

        return products.get(productNo);
    }

    /**
     * 재고상품 get
     */
    public ProductStock getStock(Long productNo) {
        checkNull(productNo);

        if(products.get(productNo) instanceof ProductStock == false) {
            throw new IllegalArgumentException(MessageManager.NOT_STOCK);
        }
        return (ProductStock) products.get(productNo);
    }

    /**
     * 클래스 get(재고상품 없는 것)
     */
    public ProductClass getClass(Long productNo) {
        checkNull(productNo);

        if(products.get(productNo) instanceof ProductStock == true) {
            throw new IllegalArgumentException(MessageManager.HAS_STOCK);
        }
        return (ProductClass) products.get(productNo);
    }

    private void checkNull(Long productNo) {
        if (products.containsKey(productNo) == false) {
            throw new NullPointerException(MessageManager.EMPTY_PRODUCT);
        }
    }



    public String productListString() {
        StringBuffer sb = new StringBuffer();
        sb.append("상품번호\t상품명\t유형\t판매가격\t재고수\n");

        for (Long key : products.keySet()) {
            Product product = products.get(key);

            sb.append(product.getProductNo()+"\t");
            sb.append(product.getName()+"\t");
            sb.append(product.getProductType().name()+"\t");
            sb.append(product.getPrice()+"\t");

            if(product instanceof ProductKit) {
                sb.append(((ProductKit) product).getStockNumber()+"\n");
            } else {
                sb.append("99999\n");
            }
        }

        return sb.toString();
    }


    /**
     * 기본상품(샘플) 생성
     */
    private static class ProductInit {
        private static final HashMap<Long, Product> products = new HashMap();

        public static HashMap<Long, Product> created() {
            products.put(16374L, new ProductClass(16374L,"스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드는 비법",151950));
            products.put(26825L, new ProductClass(26825L,"해금, 특별하고 아름다운 나만의 반려악기",114500));
            products.put(65625L, new ProductClass(65625L, "일상에 따뜻한 숨결을 불어넣어요, 반지수와 함께하는 아이패드 드로잉",174500));
            products.put(91008L, new ProductKit(91008L, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트",28000, 10));

            products.put(9236L, new ProductKit(9236L, "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누",9900, 22));
            products.put(55527L, new ProductClass(55527L, "코바늘로 인형을 만들자! 시은맘의 꼼지락 작업실",299500));
            products.put(2344L, new ProductClass(2344L, "일상 유튜버 슛뚜의 감성을 그대로. 영화같은 브이로그 제작법", 184500));
            products.put(60538L, new ProductKit(60538L, "시작에 대한 부담을 덜다. 가격 절약 아이패드 특가전",135800, 7));
            products.put(78156L, new ProductKit(78156L, "일상을 따뜻하게 채우는 썬캐쳐와 무드등",45000,16));
            products.put(53144L, new ProductClass(53144L, "여행 드로잉, 꿈만 꾸지 마세요. 핀든아트와 여행, 그리다", 249500));
            products.put(78311L, new ProductClass(78311L, "사각사각 손글씨의 낭만, 펜크래프트의 한글 정자체 펜글씨", 209500));
            products.put(97166L, new ProductKit(97166L, "이렇게 멋진 수채화 키트,어때요? 클래스101과 고넹이화방이 기획한 3가지 수채화 키트",96900,5));
            products.put(83791L, new ProductClass(83791L, "월급으로 만족하지 못하는 분들을 위한 아마존/이베이 입문",178500));
            products.put(58395L, new ProductKit(58395L, "선과 여백으로 채우는 2020년 캘린더와 엽서",18620,31));
            products.put(39712L, new ProductKit(39712L, "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", 17600, 8));
            products.put(96558L, new ProductClass(96558L, "사진 입문자를 위한 쉽게 배우고 빨리 써먹는 사진과 라이트룸", 234500));
            products.put(42031L, new ProductKit(42031L, "나만의 음악을 만들기 위한 장비 패키지", 209000, 2));
            products.put(45947L, new ProductClass(45947L, "일러스트레이터 집시의 매력적인 얼굴 그리기", 249500));

            products.put(74218L, new ProductClass(74218L, "나만의 문방구를 차려요! 그리지영의 타블렛으로 굿즈 만들기", 191600));
            products.put(28448L, new ProductClass(28448L, "당신도 할 수 있다! 베테랑 실무자가 알려주는 모션그래픽의 모든 것", 152200));

            return products;
        }
    }
}


