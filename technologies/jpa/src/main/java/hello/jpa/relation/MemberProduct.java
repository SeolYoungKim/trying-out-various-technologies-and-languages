package hello.jpa.relation;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@IdClass(MemberProduct.MemberProductId.class)
@Entity
public class MemberProduct {
    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int orderAmount;

    protected MemberProduct() {
    }

    public MemberProduct(Member member, Product product) {
        this.member = member;
        this.product = product;
    }

    public void setOrderAmount(int amount) {
        this.orderAmount = amount;
    }

    @Getter
    public static class MemberProductId implements Serializable {
        private Long member;
        private Long product;

        public MemberProductId() {
        }

        public MemberProductId(Long member, Long product) {
            this.member = member;
            this.product = product;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MemberProductId that = (MemberProductId) o;
            return Objects.equals(member, that.member) && Objects.equals(product, that.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(member, product);
        }
    }
}
