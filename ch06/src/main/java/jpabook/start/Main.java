package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    private final static EntityManager em = emf.createEntityManager();
    private final static EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {
        tx.begin();
        saveOrder();
        findOrder();
        tx.commit();
        em.close();
        emf.close();
    }

    private static void save() {
        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
//        member1.addProduct(productA);
//        member1.getProducts().add(productA);
        em.persist(member1);

        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member1);
        memberProduct.setProduct(productA);
        memberProduct.setOrderAmount(2);

        em.persist(memberProduct);
    }

//    private static void find() {
//        Member member = em.find(Member.class, "member1");
//        List<Product> products = member.getProducts();
//        for (Product product : products) {
//            System.out.println("product.name = " + product.getName());
//        }
//    }

//    private static void findInverse() {
//        Product product = em.find(Product.class, "productA");
//        List<Member> members = product.getMembers();
//        for (Member member : members) {
//            System.out.println("member = " + member.getUsername());
//        }
//    }

    private static void find() {
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember("member1");
        memberProductId.setProduct("productA");

        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);

        Member member = memberProduct.getMember();
        Product product = memberProduct.getProduct();

        System.out.println("member = " + member.getUsername());
        System.out.println("product = " + product.getName());
        System.out.println("orderAmount = " + memberProduct.getOrderAmount());
    }

    private static void saveOrder() {
        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        em.persist(member1);

        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Order order = new Order();
        order.setMember(member1);
        order.setProduct(productA);
        order.setOrderAmount(2);
        em.persist(order);
    }

    private static void findOrder() {
        Long orderId = 1L;
        Order order = em.find(Order.class, orderId);

        Member member = order.getMember();
        Product product = order.getProduct();

        System.out.println("member1 = " + member.getUsername());
        System.out.println("productA = " + product.getName());
        System.out.println("orderAmount = " + order.getOrderAmount());
    }
}

