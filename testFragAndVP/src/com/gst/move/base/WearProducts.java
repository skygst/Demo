package com.gst.move.base;

import java.util.List;

/**
 * Created by gst-pc on 2016/10/25.
 */

public class WearProducts {

    private String id;
    private String name;
    private String purchased;

    private String pic_url;
    private List<Products> listProducts;

    private List<Items> listItems;

    public List<Items> getListItems() {
        return listItems;
    }

    public void setListItems(List<Items> listItems) {
        this.listItems = listItems;
    }

    public List<Products> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Products> listProducts) {
        this.listProducts = listProducts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchased() {
        return purchased;
    }

    public void setPurchased(String purchased) {
        this.purchased = purchased;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public class Products {

        private String id;
        private String name;
        private String price;
        private String need_post;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNeed_post() {
            return need_post;
        }

        public void setNeed_post(String need_post) {
            this.need_post = need_post;
        }
    }

    public class Items {
        private String itemsId;
        private String pic_url;
        private String video_url;

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getItemsId() {
            return itemsId;
        }

        public void setItemsId(String itemsId) {
            this.itemsId = itemsId;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }

}
