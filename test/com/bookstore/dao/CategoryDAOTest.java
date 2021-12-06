package com.bookstore.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

import antlr.collections.List;

public class CategoryDAOTest extends BaseDAOTest {	
	private static CategoryDAO categoryDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		categoryDao = new CategoryDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Health");
		Category category = categoryDao.create(newCat);
		
		assertTrue(category != null && category.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		Category cat = new Category("Java Core");
		cat.setCategoryId(1);
		
		Category category = categoryDao.update(cat);
		
		assertEquals(cat.getName(), category.getName());
	}

	@Test
	public void testGet() {
		Integer catId = 2;
		Category cat = categoryDao.get(catId);
		
		assertNotNull(cat);
	}

	@Test
	public void testDeleteCategory() {
		Integer catId = 7;
		categoryDao.delete(catId);
		
		Category cat = categoryDao.get(catId);
		
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		//List<Category> listCategory = categoryDao.listAll();
		
		//assertTrue(listCategory.size() > 0);
	}

	@Test
	public void testCount() {
		long totalCategories = categoryDao.count();
		
		assertEquals(5, totalCategories);
	}
	
	@Test
	public void testFindBName() {
		String name = "Java Core";
		Category category = categoryDao.findByName(name);
		
		assertNotNull(category);
	}

	@Test
	public void testFindBNameNotFound() {
		String name = "Java Plus";
		Category category = categoryDao.findByName(name);
		
		assertNull(category);
	}
}
