package com.pizzabaker.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.pizzabaker.daos.BasePizzaDAO;
import com.pizzabaker.daos.CorruptedDBException;
import com.pizzabaker.daos.DBConnectionException;
import com.pizzabaker.daos.IngredientDAO;
import com.pizzabaker.daos.NoDataException;
import com.pizzabaker.daos.OrderDAO;
import com.pizzabaker.daos.SupplierDAO;
import com.pizzabaker.entities.BasePizza;
import com.pizzabaker.entities.Ingredient;
import com.pizzabaker.entities.IngredientDetail;
import com.pizzabaker.entities.Order;
import com.pizzabaker.entities.OrderIngredient;
import com.pizzabaker.entities.Supplier;

@Controller
public class MainController {

	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------ ORDERS ------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/")
	public RedirectView home(Model model) {
		return new RedirectView("/orders");
	}
	
	@GetMapping("/orders")
	public String orders(Model model) {
		List<Order> orders = null;
		try {
			orders = new OrderDAO().selectOrders();
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
		model.addAttribute("orders", orders);
		return "orders";
	}

	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------- SUPPLIERS  ----------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------

	@GetMapping("/suppliers")
	public String suppliers(Model model) {
		List<Supplier> suppliers = null;
		try {
			suppliers = new SupplierDAO().selectSuppliers(true);
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
		model.addAttribute("suppliers", suppliers);
		return "suppliers";
	}
	
	@GetMapping("/hideShowSupplier")
	public RedirectView hideShowSupplier(Model model, @RequestParam long id) {
		SupplierDAO supplierDAO = new SupplierDAO();
		try {
			Supplier supplier = supplierDAO.selectSupplierById(id);
			supplier.setHidden(!supplier.isHidden());
			supplierDAO.updateSupplier(supplier);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		return new RedirectView("suppliers");
	}
	
	@PostMapping("/addSupplier")
	public RedirectView addSupplier(Model model, @RequestParam("name") String name, @RequestParam("ingredients") String ingredients) {
		SupplierDAO supplierDAO = new SupplierDAO();
		try {
			supplierDAO.insertSupplier(new Supplier(-1, name, ingredients, false));
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		return new RedirectView("suppliers");
	}
	
	@PostMapping("/editSupplier")
	public RedirectView editSupplier(Model model, @RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("ingredients") String ingredients, @RequestParam("isHidden") boolean isHidden) {
		SupplierDAO supplierDAO = new SupplierDAO();
		try {
			supplierDAO.updateSupplier(new Supplier(id, name, ingredients, isHidden));
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		return new RedirectView("suppliers");
	}
	
	@PostMapping("/deleteSupplier")
	public RedirectView deleteSupplier(Model model, @RequestParam("id") long id) {
		SupplierDAO supplierDAO = new SupplierDAO();
		try {
			supplierDAO.deleteSupplier(id);
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		return new RedirectView("suppliers");
	}
	
	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------- INGREDIENTS  ---------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/ingredients")
	public String ingredients(Model model) {
		List<Ingredient> ingredients = null;
		try {
			ingredients = new IngredientDAO().selectIngredients(true);
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
		List<IngredientTableRow> ingredientTableRows = new ArrayList<>();
		for(Ingredient ingredient : ingredients) {
			for(IngredientDetail detail : ingredient.getIngredientDetails()) {
				long detailId = detail.getId();
				String name = ingredient.getName();
				String province = detail.getProvince();
				String supplier = detail.getSupplier().getName();
				double price = detail.getPrice();
				int quantity = detail.getQuantity();
				boolean visibility = detail.isHidden();
				ingredientTableRows.add(new IngredientTableRow(detailId, name, province, supplier, price, quantity, visibility));
			}
		}
		List<Supplier> suppliers = null;
		try {
			suppliers = new SupplierDAO().selectSuppliers(false);
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("ingredientTableRows", ingredientTableRows);
		return "ingredients";
	}
	
	@PostMapping("/deleteIngredientDetail")
	public RedirectView deleteIngredientDetail(Model model, @RequestParam("id") long id) {
		IngredientDAO ingredientDAO = new IngredientDAO();
		try {
			ingredientDAO.deleteIngredientDetail(id);
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		return new RedirectView("ingredients");
	}
	
	@PostMapping("/hideShowIngredientDetail")
	public RedirectView hideShowIngredientDetail(Model model, @RequestParam("id") long id) {
		IngredientDAO ingredientDAO = new IngredientDAO();
		try {
			ingredientDAO.changeVisibilityIngredientDetail(id);
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		return new RedirectView("ingredients");
	}
	
	@PostMapping("/addIngredient")
	public RedirectView hideShowIngredientDetail(Model model, @RequestParam("name") String name, @RequestParam("details") String[] details) {
		Map<Long, Supplier> mapSuppliersById = null;
		try {
			mapSuppliersById = new SupplierDAO().getMapSuppliersById();
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		List<IngredientDetail> ingredientDetails = new ArrayList<>();
		for(String detail : details) {
			String[] vec = detail.split("###");
			long id = -1;
			String province = vec[0];
			double price = Double.parseDouble(vec[2]);
			int quantity = 0;
			long supplierId = Long.parseLong(vec[1]);
			Supplier supplier = mapSuppliersById.get(supplierId);
			boolean hidden = false;
			IngredientDetail ingredientDetail = new IngredientDetail(id, province, price, quantity, supplier, hidden);
			ingredientDetails.add(ingredientDetail);
		}
		Ingredient ingredient = new Ingredient(-1, name, ingredientDetails);
		try {
			new IngredientDAO().insertIngredient(ingredient);
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		return new RedirectView("ingredients");
	}
	
	@PostMapping("/restockIngredient")
	public RedirectView restockIngredientDetail(Model model, @RequestParam("id") long id, @RequestParam("quantity") int quantity) {
		IngredientDAO ingredientDAO = new IngredientDAO();
		try {
			ingredientDAO.restockIngredientDetail(id, quantity);
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		return new RedirectView("ingredients");
	}
	
	@PostMapping("/updateIngredient")
	public RedirectView updateIngredientDetail(Model model, @RequestParam("ingDetailId") long ingDetailId, @RequestParam("name") String name, @RequestParam("region") String region, @RequestParam("price") double price) {
		IngredientDAO ingredientDAO = new IngredientDAO();
		try {
			ingredientDAO.updateIngredient(ingDetailId, name, region, price);
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		return new RedirectView("ingredients");
	}
	
	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------- ORDER YOUR PIZZA -------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------

	@GetMapping("/order-your-pizza")
	public String orderYourPizza(Model model) {
		List<BasePizza> basePizzas = null;
		List<Ingredient> ingredients = null;
		try {
			basePizzas = new BasePizzaDAO().selectBasePizzas();
			ingredients = new IngredientDAO().selectIngredients(false);
		} catch (DBConnectionException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
		model.addAttribute("basePizzas", basePizzas);
		model.addAttribute("ingredients", ingredients);
		return "order-your-pizza";
	}
	
	@PostMapping("/addOrder")
	public RedirectView addOrder(Model model, @RequestParam("basePizzaId") long basePizzaId, @RequestParam("ingredients") String[] ingredients) {
		try {
			Map<Long, BasePizza> mapBasePizzasById = new BasePizzaDAO().getMapBasePizzasById();
			BasePizza basePizza = mapBasePizzasById.get(basePizzaId);
			List<OrderIngredient> listOrderIngredients = new ArrayList<>();
			for(String str : ingredients) {
				String[] vec = str.split("###");
				long ingredientDetailId = Long.parseLong(vec[0]);
				int quantity = Integer.parseInt(vec[1]);
				double price = Double.parseDouble(vec[2]);
				listOrderIngredients.add(new OrderIngredient(null, new IngredientDetail(ingredientDetailId, null, -1, -1, null, false), quantity, price));
			}
			Order order = new Order(-1, new Date(), basePizza, listOrderIngredients);
			new OrderDAO().insertOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			new RedirectView("error");
		}
		return new RedirectView("order-your-pizza");
	}
}
