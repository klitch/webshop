package entity;

import java.util.Arrays;

/**
 * Created by Vitalii_Bandura on 5/5/2015.
 */
public class FilterBean {
    private String[] gender;
    private String[] price;
    private String[] brand;
    private String[] frameSize;
    private String sortBy;
    private String sortDirection;
    private String model;
    private int perPage;
    private int pageNumber;
    private int numberOfPages;

    public String[] getGender() {
        return gender;
    }

    public void setGender(String[] gender) {
        this.gender = gender;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        if (perPage != null && !perPage.isEmpty()) {
            int perPageInt = Integer.parseInt(perPage);
            if(perPageInt > 0){
                this.perPage = perPageInt;
            }else{
                this.perPage = 9;
            }
        } else {
            this.perPage = 9;
        }
    }

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public String[] getBrand() {
        return brand;
    }

    public void setBrand(String[] brand) {
        this.brand = brand;
    }

    public String[] getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(String[] frameSize) {
        this.frameSize = frameSize;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        if (pageNumber != null && !pageNumber.isEmpty()) {
            int pageNumberInt = Integer.parseInt(pageNumber);
            if(pageNumberInt > 0){
                this.pageNumber = pageNumberInt;
            }else{
                this.pageNumber = 1;
            }
        } else {
            this.pageNumber = 1;
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FilterBean() {
    }

    public static BeanBuilder getBuilder() {
        return new FilterBean().new BeanBuilder();
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }


    public class BeanBuilder {

        public BeanBuilder() {
        }

        public BeanBuilder gender(String[] gender) {
            if(gender != null) {
                gender = Arrays.copyOf(gender, gender.length + 1);
                gender[gender.length-1] = "Unisex";
            }
            FilterBean.this.gender = gender;
            return this;
        }

        public BeanBuilder price(String[] price) {
            FilterBean.this.price = price;
            return this;
        }

        public BeanBuilder brand(String[] brand) {
            FilterBean.this.brand = brand;
            return this;
        }

        public BeanBuilder frameSize(String[] frameSize) {
            FilterBean.this.frameSize = frameSize;
            return this;
        }

        public BeanBuilder sortBy(String sortBy) {
            FilterBean.this.sortBy = sortBy;
            return this;
        }

        public BeanBuilder sortDirection(String sortDirection) {
            FilterBean.this.sortDirection = sortDirection;
            return this;
        }

        public BeanBuilder perPage(String perPage) {
            FilterBean.this.setPerPage(perPage);
            return this;
        }

        public BeanBuilder pageNumber(String pageNumber) {
            FilterBean.this.setPageNumber(pageNumber);
            return this;
        }

        public BeanBuilder numberOfPages(int numberOfPages) {
            FilterBean.this.numberOfPages = numberOfPages;
            return this;
        }

        public BeanBuilder model(String model) {
            FilterBean.this.model = model;
            return this;
        }

        public FilterBean build() {
            return FilterBean.this;
        }

    }
}
