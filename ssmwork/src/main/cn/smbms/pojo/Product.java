package cn.smbms.pojo;

public class Product {
        private Integer id;
        private Integer proId;
        private String proName;
        private String proCode;
        private String proType;
        private String proDesc;
        private Integer price;
        private String unit;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Integer getProId() {
                return proId;
        }

        public void setProId(Integer proId) {
                this.proId = proId;
        }

        public String getProName() {
                return proName;
        }

        public void setProName(String proName) {
                this.proName = proName;
        }

        public String getProCode() {
                return proCode;
        }

        public void setProCode(String proCode) {
                this.proCode = proCode;
        }

        public String getProType() {
                return proType;
        }

        public void setProType(String proType) {
                this.proType = proType;
        }

        public String getProDesc() {
                return proDesc;
        }

        public void setProDesc(String proDesc) {
                this.proDesc = proDesc;
        }

        public Integer getPrice() {
                return price;
        }

        public void setPrice(Integer price) {
                this.price = price;
        }

        public String getUnit() {
                return unit;
        }

        public void setUnit(String unit) {
                this.unit = unit;
        }
}
