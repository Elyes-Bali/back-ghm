package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.ProductType;
import tn.esprit.vitanova.entities.Products;
import tn.esprit.vitanova.entities.Sale;
import tn.esprit.vitanova.repository.ProductRepo;
import tn.esprit.vitanova.repository.SaleRepo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService implements IProductservice{
    ProductRepo productRepo;
    private static final int THRESHOLD_PERCENTAGE = 20; // 20% increase threshold
    private SaleRepo saleRepo;

    @Override
    public Products ajouterProducts(Products p) {
        return productRepo.save(p);
    }

    @Override
    public void updateProducts(Long idProducts, Products p) {
        p.setIdProducts(idProducts);
        productRepo.save(p);

    }

    @Override
    public Products getProductsbyId(Long idProducts) {
        return productRepo.findById(idProducts).get();
    }

    @Override
    public List<Products> chercherTousProducts() {
        return productRepo.findAll();
    }

    @Override
    public void supprimerproducts(Long idProducts) {
    productRepo.deleteById(idProducts);
    }

    @Override
    public Products getproductId(Long idProducts) {
        return productRepo.findById(idProducts).orElse(null);
    }

    @Override
    public List<Products> getChartData(String metric) {
        return null;
    }

    @Override
    public Map<ProductType, Long> countProductsByTypeProd() {
        Map<ProductType, Long> countMap = new HashMap<>();
        for (ProductType type : ProductType.values()) {
            countMap.put(type, productRepo.countByTypeProd(type));
        }
        return countMap;
    }

    @Override
    public Map<ProductType, Long> calculateTotalPriceByTypeProd() {
        Map<ProductType, Long> totalPriceMap = new HashMap<>();
        for (ProductType type : ProductType.values()) {
            Long totalPrice = productRepo.sumPriceByTypeProd(type);
            totalPriceMap.put(type, totalPrice != null ? totalPrice : 0);
        }
        return totalPriceMap;
    }

    @Override
    public void sellProduct(Long idProducts, int quantitySold) {
        Products product = productRepo.findById(idProducts).orElse(null);
        if (product != null && product.getQuantityP() >= quantitySold) {
            // Create Sale entity
            Sale sale = new Sale();
            sale.setProduct(product);
            sale.setQuantitySold(quantitySold);
            sale.setSaleDate(new Date()); // Set current time as sale date

            // Decrease quantity sold from total quantity
            product.setQuantityP(product.getQuantityP() - quantitySold);
            productRepo.save(product);

            // Save sale entity
            saleRepo.save(sale);
        }  else {
            throw new IllegalArgumentException("Invalid product ID or insufficient quantity.");
        }
    }

//    @Override
//    public boolean shouldIncreaseQuantity(Long idProducts) {
//        // Retrieve quantities sold for the current month and last month
//        int quantitySoldThisMonth = saleRepo.findQuantitySoldThisMonth(idProducts);
//        int quantitySoldLastMonth = saleRepo.findQuantitySoldLastMonth(idProducts);
//
//        // Calculate the increase percentage between this month and last month
//        int increasePercentage = calculateIncreasePercentage(quantitySoldThisMonth, quantitySoldLastMonth);
//
//        // Determine if quantity should be increased based on a threshold percentage
//        boolean increaseNeeded = increasePercentage > THRESHOLD_PERCENTAGE;
//
//        return increaseNeeded;
//    }
//
//    @Override
//    public int calculateIncreasePercentage(int thisMonth, int lastMonth) {
//        if (lastMonth == 0) return 0; // Prevent division by zero
//        return ((thisMonth - lastMonth) * 100) / lastMonth;
//    }

}
