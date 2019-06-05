package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@NoArgsConstructor
public class Warehouse {
	// == fields ==

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private User manager;

//	@NotNull
	@OneToMany
			(mappedBy = "warehouse")
	private List<Stock> stocks;

	@Transient
	private String warehouseAndManager;

	public String getWarehouseAndManager() {
		return name + " : " + manager.getName();
	}

	public static Warehouse empty(){
		return new Warehouse();
	}


	//	@NotNull
//	@OneToMany
//	private List<Stock> availableStock;
//
//	@NotNull
//	@OneToMany
//	private List<Stock> reservedStock;
//
//	@NotNull
//	@OneToMany
//	private List<Stock> totalStock;

//	@Transient
//	private Map<Item,Integer> availableStockByItemMap
}
