package delivery.domain;

    // 더미 데이터 만들어서 사용
public class DeliveryCmp {
    // 택배사 id
    private Long id;
    // 택배사 이름
    private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "DeliveryCmp{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

}
