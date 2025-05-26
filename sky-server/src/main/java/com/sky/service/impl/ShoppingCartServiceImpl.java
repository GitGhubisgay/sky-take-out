package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        // 1.添加购物车商品是否在购物车中
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        // 2.如果在购物车中，数量+1，不存在，添加到购物车中
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        if (list != null && list.size() > 0){
            ShoppingCart cart = list.get(0);
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.updateNumberById(cart);
        }else{
            //商品或者套餐信息获取
            Long dishId = shoppingCart.getDishId();
            if(dishId != null){
                //添加到购物车的是菜品
                Dish dish = dishMapper.getById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            }else{
                //添加到购物车的是套餐
                Long setmealId = shoppingCart.getSetmealId();
                Setmeal setmeal = setmealMapper.getById(setmealId);
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            shoppingCart.setNumber(1);   ////数量默认为1
            shoppingCart.setCreateTime(LocalDateTime.now()); ///创建时间
            shoppingCartMapper.insert(shoppingCart);
        }
    }

    /**
     * 显示购物车
     * @return
     */
    @Override
    public List<ShoppingCart> showShoppingCart() {
        Long currentId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(currentId)
                .build();
        List<ShoppingCart>  list = shoppingCartMapper.list(shoppingCart);
        return list;
    }

    /**
     * 清空购物车
     */
    @Override
    public void cleanShoppingCart() {
        Long currentId = BaseContext.getCurrentId();
        shoppingCartMapper.deleteByUserId(currentId);
    }


    /**
     * 减少购物车商品数量
     * @param shoppingCartDTO
     */
    @Override
    public void subNumber(ShoppingCartDTO shoppingCartDTO) {
        // 1.根据id查询购物车数据
        Long dishId = shoppingCartDTO.getDishId();
        ShoppingCart shoppingCart = shoppingCartMapper.getByDishId(dishId);
        if (shoppingCart.getNumber() == 1){
            shoppingCartMapper.deleteById(dishId);
        }else if (shoppingCart.getNumber() > 1){
            shoppingCart.setNumber(shoppingCart.getNumber() - 1);
            shoppingCartMapper.updateNumberById(shoppingCart);
        }else{
            throw new RuntimeException("不能再减了");
        }

    }
}
