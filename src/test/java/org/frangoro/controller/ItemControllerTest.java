package org.frangoro.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Matchers.isA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.frangoro.domain.Items;
import org.frangoro.domain.ItemsTransLoc;
import org.frangoro.dto.ItemDto;
import org.frangoro.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import flexjson.JSONSerializer;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:springTestContext.xml", "classpath:mvc-dispatcher-servlet.xml"})
public class ItemControllerTest {

	final static Logger logger = LoggerFactory.getLogger(ItemControllerTest.class);
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    @Autowired
    private ItemService itemService;
    
    @Before
    public void setup() {
    	Mockito.reset(itemService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
    /**
     * Test search when the item exists.
     * 
     * @throws Exception
     */
    @Test
    public void showExistingItem() throws Exception {
    	// https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
    	// Mockea el servicio.
    	// Para ello se inyecta el servicio como un mock (ver springTestContext.xml)
    	// y se definen la salida que tendrá según la entrada
    	final Long ID = new Long(1);
    	ItemDto myItem = new ItemDto();
    	myItem.setId(ID);
    	myItem.setCode("T");
    	when(itemService.getInfo(ID)).thenReturn(myItem);
    	
        this.mockMvc.perform(get("/item/{id}",1).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        	.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.code").value("T"));
        
//        verify(itemService, times(1)).getInfo(ID);
//        verifyNoMoreInteractions(itemService);
    }
    
    /**
     * Test search when the item don't exists.
     * 
     * @throws Exception
     */
    @Test
    public void showNoExistingItem() throws Exception {
    	
    	final Long ID = new Long(1);
    	when(itemService.getInfo(ID)).thenReturn(null);
    	
    	mockMvc.perform(get("/item/{id}",ID).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
    	.andDo(print()).andExpect(status().isNotFound());
    }
    
    /**
     * Test list all items.
     * 
     * @throws Exception
     */
    @Test
    public void queryAllItems() throws Exception {
    	
    	//TODO: Meter todo este codigo duplicado en el before?
    	ItemsTransLoc item1 = new ItemsTransLoc();
    	item1.setId(1);
    	item1.setCode("a");
    	
    	ItemsTransLoc item2 = new ItemsTransLoc();
    	item2.setId(2);
    	item2.setCode("b");
    	
    	List<ItemsTransLoc> items = new ArrayList<ItemsTransLoc>();
    	items.add(item1);
    	items.add(item2);
    	when(itemService.getItems()).thenReturn(items);
    	
        this.mockMvc.perform(get("/item").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        	.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.length()").value(2));
    }
    
    /**
     * Test list filtered items.
     * 
     * @throws Exception
     */
    @Test
    public void queryFilterItems() throws Exception {
    	
    	//TODO: Meter todo este codigo duplicado en el before?
    	ItemsTransLoc item1 = new ItemsTransLoc();
    	item1.setId(1);
    	item1.setCode("b");
    	
    	ItemsTransLoc item2 = new ItemsTransLoc();
    	item2.setId(2);
    	item2.setCode("b");
    	
    	List<ItemsTransLoc> items = new ArrayList<ItemsTransLoc>();
    	items.add(item1);
    	items.add(item2);
    	
    	Map<String, String> filterMap = new HashMap<String, String>();
    	filterMap.put("code", "b");
    	
    	when(itemService.queryFilter(filterMap)).thenReturn(items);

    	this.mockMvc.perform(get("/item").param("filter", "[{\"property\":\"code\",\"value\":\"b\"}]")
    			.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
    	.andDo(print())
    	.andExpect(status().isOk())
    	.andExpect(content().contentType("application/json;charset=UTF-8"))
    	.andExpect(jsonPath("$.length()").value(2));
    }
    
    /**
     * Test create one item.
     * 
     * @throws Exception
     */
    @Test
    public void createItem() throws Exception {
    	
    	when(itemService.create(isA(ItemDto.class))).thenReturn(true);
    	
    	// Prepare input
    	ItemDto itemDto = new ItemDto();
    	itemDto.setId(1l);
    	itemDto.setCode("b");
    	
    	String requestBodyString = new JSONSerializer().serialize(itemDto);
    	
        this.mockMvc.perform(post("/item")
//        	.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        	.contentType(MediaType.APPLICATION_JSON)
        		.content(requestBodyString)
        	)
        	.andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.code").value("b"));
    }
    
    /**
     * Test update one item with code a to code b.
     * 
     * @throws Exception
     */
    @Test
    public void updateItem() throws Exception {

    	// getItem service input
    	Items item = new Items();
    	item.setId(1l);
    	item.setCode("a");
    	
    	when(itemService.getItem(1l)).thenReturn(item);
    	when(itemService.update(isA(Items.class))).thenReturn(true);
    	
    	// Test input
    	ItemDto itemDto = new ItemDto();
    	itemDto.setId(1l);
    	itemDto.setCode("b");
    	
    	String requestBodyString = new JSONSerializer().serialize(itemDto);
    	
    	this.mockMvc.perform(put("/item/{id}",1)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(requestBodyString)
    			)
	    	.andDo(print())
	    	.andExpect(status().isOk())
	    	.andExpect(content().contentType("application/json;charset=UTF-8"))
	    	.andExpect(jsonPath("$.id").value(1))
	    	.andExpect(jsonPath("$.code").value("b"));
    }
    
    /**
     * Test delete one item.
     * 
     * @throws Exception
     */
    @Test
    public void deleteItem() throws Exception {
    	Items item = new Items();
    	item.setId(1l);
    	item.setCode("b");
    	
    	when(itemService.getItem(1l)).thenReturn(item);
    	when(itemService.delete(1l)).thenReturn(true);
    	
    	this.mockMvc.perform(delete("/item/{id}",1)
    			.contentType(MediaType.APPLICATION_JSON)
    			)
    	.andDo(print())
    	.andExpect(status().isOk());
    }
}