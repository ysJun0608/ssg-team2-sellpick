package inventory.domain;

public class WarehouseSection {
    //섹션 id
    private Long id;
    //창고 id(fk)
    private Long warehouseId;
    //색션 이름
    private String sectionName;

    @Override
    public String toString() {
        return "WarehouseSection{" +
                "id=" + id +
                ", warehouseId=" + warehouseId +
                ", sectionName='" + sectionName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
