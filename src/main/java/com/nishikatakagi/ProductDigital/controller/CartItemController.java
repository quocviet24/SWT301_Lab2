package com.nishikatakagi.ProductDigital.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.CartItem;
import com.nishikatakagi.ProductDigital.service.CardTypeService;
import com.nishikatakagi.ProductDigital.service.CartItemService;
import com.nishikatakagi.ProductDigital.service.UserService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/cart")
@Controller
public class CartItemController {

    @Autowired
    HttpSession session;
    @Autowired
    CardTypeService cardTypeService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String addToCart(RedirectAttributes redirectAttributes,
            @RequestParam(value = "cardTypeId", required = false) Integer cardTypeId,
            @RequestParam(value = "quantity", required = false) Integer quantity) {
        UserSessionDto userSession = (UserSessionDto) session.getAttribute("user_sess");
        if (userSession == null) {
            return "redirect:/login";
        }
        String message = cartItemService.addItemToCart(userSession.getUsername(), cardTypeId, quantity);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/product";
    }

    @GetMapping("")
    public String showCartDetails(Model model) {
        UserSessionDto user = (UserSessionDto) session.getAttribute("user_sess");
        if (user != null) {
            List<CartItem> cart = cartItemService.getCartDetails(user.getUsername());
            System.out.println(cart.size());
            model.addAttribute("cart", cart);
            return "publics/cart";
        } else {
            // User is not logged in, redirect to login page
            return "redirect:/login"; // Replace '/login' with your actual login URL
        }
    }

    @DeleteMapping("/delete")
    public String deleteCartItem(@RequestParam("cartItemId") int cartItemId, RedirectAttributes redirectAttributes) {
        // Check if user is logged in
        UserSessionDto user = (UserSessionDto) session.getAttribute("user_sess");
        if (user != null) {
            // Delete the cart item
            cartItemService.deleteCartItem(cartItemId);
            // redirectAttributes.addFlashAttribute("success", "Item removed from cart
            // successfully!");
            return "redirect:/cart";
        } else {
            // User is not logged in, redirect to login page
            return "redirect:/login";
        }
    }

    @GetMapping("/updateQuantity")
    @ResponseBody
    public ResponseEntity<String> updateQuantity(@RequestParam int quantity, @RequestParam int cardTypeId) {
        UserSessionDto user = (UserSessionDto) session.getAttribute("user_sess");
        if (user != null) {
            try {
                // Update the quantity in the database using username
                CartItem updatedCartItem = cartItemService.updateItemQuantity(user.getUsername(), cardTypeId, quantity);

                if (updatedCartItem != null) {
                    return ResponseEntity.ok("Quantity updated successfully");
                } else {
                    return ResponseEntity.badRequest().body("Failed to update quantity");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during update");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
    }

    @GetMapping("/itemsCount")
    @ResponseBody
    public ResponseEntity<?> getCartItemCount() {
        UserSessionDto user = (UserSessionDto) session.getAttribute("user_sess");

        // Create a map to return both loggedIn status and item count
        Map<String, Object> response = new HashMap<>();
        response.put("loggedIn", user != null);

        // if user logs in, return the number of items in cart.
        if (user != null) {
            try {
                response.put("cartItemCount", cartItemService.getCartItemCount(user.getUsername()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            // if user has not logged in yet, return 0
            response.put("cartItemCount", 0);
        }

        return ResponseEntity.ok(response);
    }

}
