package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dtoRequest.LoginRequestDto;
import com.ecommerce.dtoRequest.ProductRequestDto;
import com.ecommerce.dtoRequest.SellerRequestDto;
import com.ecommerce.dtoResponse.LoginResponseDto;
import com.ecommerce.dtoResponse.ProductResponseDto;
import com.ecommerce.dtoResponse.SellerResponseDto;
import com.ecommerce.entity.Brand;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Products;
import com.ecommerce.entity.Seller;
import com.ecommerce.entity.SellerInfo;
import com.ecommerce.entity.Tags;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.TagNotFoundException;
import com.ecommerce.repository.BrandRepository;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.SellerInfoRepository;
import com.ecommerce.repository.SellerRepository;
import com.ecommerce.repository.TagRepository;
import com.ecommerce.exception.BrandNotFoundException;

@Service
public class ProductService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    
    @Autowired
    private ProductRepository productRepository;

  @Autowired
  private TagRepository tagRepository;
  
  @Autowired
  private CategoryRepository categoryRepository;
  
  @Autowired
  private BrandRepository brandRepository;
  
  
    // REGISTER SELLER
    public SellerResponseDto registerSeller(SellerRequestDto dto) {

        SellerInfo sellerInfo = new SellerInfo();

        sellerInfo.setSellerName(dto.getSellerName());
        sellerInfo.setSellerAddress(dto.getSellerAddress());

        SellerInfo savedInfo = sellerInfoRepository.save(sellerInfo);

        Seller seller = new Seller();

        seller.setSellerUsername(dto.getSellerUsername());
        seller.setSellerPassword(dto.getSellerPassword());

        seller.setSellerInfo(savedInfo);

        Seller savedSeller = sellerRepository.save(seller);

        SellerResponseDto response = new SellerResponseDto();

        response.setSellerId(savedSeller.getSellerId());
        response.setSellerUsername(savedSeller.getSellerUsername());

        response.setSellerName(
                savedSeller.getSellerInfo().getSellerName());

        response.setSellerAddress(
                savedSeller.getSellerInfo().getSellerAddress());

        return response;
    }

    // LOGIN
    public LoginResponseDto login(LoginRequestDto dto) {

        Optional<Seller> seller =
                sellerRepository.findBySellerUsername(dto.getUsername());

        LoginResponseDto response = new LoginResponseDto();

        if (seller.isPresent() &&
                seller.get().getSellerPassword()
                        .equals(dto.getPassword())) {

            response.setMessage("Login Successful");
            response.setSuccess(true);

        } else {

            response.setMessage("Invalid Username or Password");
            response.setSuccess(false);
        }

        return response;
    }


    // COMMON RESPONSE METHOD
    private ProductResponseDto mapToResponse(Products product) {

        ProductResponseDto response = new ProductResponseDto();

        // Product
        response.setProductId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setProductDescription(product.getProductDescription());
        response.setProductQuantity(product.getProductQuantity());
        response.setProductMrpPrice(product.getProductMrpPrice());
        response.setProductSellingPrice(product.getProductSellingPrice());
        response.setReturnable(product.getReturnable());
        response.setRefundable(product.getRefundable());

        // Seller
        if (product.getSeller() != null &&
                product.getSeller().getSellerInfo() != null) {

            response.setSellerName(
                    product.getSeller()
                            .getSellerInfo()
                            .getSellerName());
        }

        // Category
        if (product.getCategory() != null) {

            response.setCategoryId(product.getCategory().getCategoryId());
            response.setCategoryName(product.getCategory().getCategoryName());
        }

        // Brand
        if (product.getBrand() != null) {

            response.setBrandId(product.getBrand().getBrandId());
            response.setBrandName(product.getBrand().getBrandName());
        }

        return response;
    }

    
    //addProduct()
    
    public ProductResponseDto addProduct(ProductRequestDto dto) {

        Seller seller = sellerRepository.findById(dto.getSellerId())
                .orElseThrow(() ->
                        new RuntimeException("Seller Not Found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category Not Found with id : "
                                        + dto.getCategoryId()));

        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() ->
                        new BrandNotFoundException(
                                "Brand Not Found with id : "
                                        + dto.getBrandId()));

        Products product = new Products();

        product.setProductName(dto.getProductName());
        product.setProductDescription(dto.getProductDescription());
        product.setProductQuantity(dto.getProductQuantity());
        product.setProductMrpPrice(dto.getProductMrpPrice());
        product.setProductSellingPrice(dto.getProductSellingPrice());
        product.setReturnable(dto.getReturnable());
        product.setRefundable(dto.getRefundable());

        product.setSeller(seller);
        product.setCategory(category);
        product.setBrand(brand);

        Products savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }
    
    
 // GET ALL PRODUCTS
    public List<ProductResponseDto> getAllProducts() {

        List<Products> products = productRepository.findAll();

        List<ProductResponseDto> responseList = new ArrayList<>();

        for (Products product : products) {

            responseList.add(mapToResponse(product));
        }

        return responseList;
    }

    
    // GET PRODUCT BY ID
    public ProductResponseDto getProductById(Integer productId) {

        Products product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Not Found with id: " + productId));

        return mapToResponse(product);
    }

 // UPDATE PRODUCT
    public ProductResponseDto updateProduct(
            Integer productId,
            ProductRequestDto dto) {

        Products product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Not Found with id : "
                                        + productId));

        Seller seller = sellerRepository.findById(dto.getSellerId())
                .orElseThrow(() ->
                        new RuntimeException("Seller Not Found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category Not Found with id : "
                                        + dto.getCategoryId()));

        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() ->
                        new BrandNotFoundException(
                                "Brand Not Found with id : "
                                        + dto.getBrandId()));

        product.setProductName(dto.getProductName());
        product.setProductDescription(dto.getProductDescription());
        product.setProductQuantity(dto.getProductQuantity());
        product.setProductMrpPrice(dto.getProductMrpPrice());
        product.setProductSellingPrice(dto.getProductSellingPrice());
        product.setReturnable(dto.getReturnable());
        product.setRefundable(dto.getRefundable());

        product.setSeller(seller);
        product.setCategory(category);
        product.setBrand(brand);

        Products updatedProduct = productRepository.save(product);

        return mapToResponse(updatedProduct);
    }
    
    //Add Tag To Product
    
    public String addTagToProduct(Integer productId, Integer tagId) {

        Products product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Not Found with id: " + productId));

        Tags tag = tagRepository.findById(tagId)
                .orElseThrow(() ->
                        new TagNotFoundException(
                                "Tag Not Found with id: " + tagId));

        product.getTags().add(tag);

        productRepository.save(product);

        return "Tag Added Successfully";
    }
    
    
    //Remove Tag from product
    
    public String removeTagFromProduct(Integer productId, Integer tagId) {

        Products product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Not Found with id: " + productId));

        Tags tag = tagRepository.findById(tagId)
                .orElseThrow(() ->
                        new TagNotFoundException(
                                "Tag Not Found with id: " + tagId));

        product.getTags().remove(tag);

        productRepository.save(product);

        return "Tag Removed Successfully";
    }
    
    
    public void deleteProduct(Integer productId) {

        Products product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Not Found with id: " + productId));

        productRepository.delete(product);
    }
    
    
}