package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.item.ItemService;
import com.arek.warehousetransfer.utils.AttributeNames;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("stock")
public class StockController {

	private StockService stockService;
	private WarehouseService warehouseService;
	private ItemService itemService;

	//REST
	@PutMapping("add")
	public ResponseEntity<String> saveNewStock(@RequestBody StockAndWarehouseWrapper wrapper) {
		Stock stock = wrapper.getStock();
		Warehouse warehouse = warehouseService.findWarehouseById(wrapper.getWarehouse().getId());

		Item item;
		if (stock.getItem().getId() == null) {
			item = stock.getItem();
		} else {
			item = itemService.findItemById(stock.getItem().getId());
		}

		stockService.updateStockInWarehouse(stock.getItemStock(), item,
				warehouse, StockType.AVAILABLE, false);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
