package com.hihi.service;

import com.hihi.model.Product;

import java.util.*;

public class ProductService  implements IProductService<Product> {

    public static final Map<Integer,Product> products;
    static {
        products=new HashMap<>();
        products.put(1,new Product(1,"Samsung Ultra 20","100$","Smart phone","Samsung"));
        products.put(2,new Product(2,"Iporn 12 Promax","11$","Cui bap phone","Apple"));
        products.put(3,new Product(3,"Macbook Air M1","20$","Cui bap laptop","Apple"));
        products.put(4,new Product(4,"Dell Inprison","1000$","Laptop vjp pro chay window 7","Dell"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void update(int id, Product product) {
        products.put(id,product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    public List<Product> findByName(String name){
        List<Product>productList= new ArrayList<>();
        List<Product>list=findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                productList.add(list.get(i));
            }
        }
        return productList;
    }

    public List<Product> findByProducer(String name){
        List<Product> list = new ArrayList<>();
        Set<Integer> keySet = products.keySet();
        for (Integer key: keySet ) {
            if (products.get(key).getProducer().toLowerCase().contains(name.toLowerCase())){
                list.add(products.get(key));
            }
        }
        return list;
    }
}
