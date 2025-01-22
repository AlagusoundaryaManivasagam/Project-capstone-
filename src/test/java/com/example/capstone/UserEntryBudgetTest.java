package com.example.capstone;

import com.example.capstone.database.dao.BudgetTestDAO;
import com.example.capstone.database.dao.EntryTestDAO;
import com.example.capstone.database.dao.UserTestDAO;
import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.Entry;
import com.example.capstone.database.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDate;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UserEntryBudgetTest {

    private static final Logger logger = LoggerFactory.getLogger(UserEntryBudgetTest.class);

    private UserTestDAO userTestDAO = new UserTestDAO();
    private EntryTestDAO entryTestDAO = new EntryTestDAO();
    private BudgetTestDAO budgetTestDAO = new BudgetTestDAO();

    @Order(1)
    @Test
    public void createUserTest(){
        User given = new User();
        given.setEmail("test@example.com");
        given.setPassword("password");
        given.setUserName("test");
        User actual = userTestDAO.createUser(given);
        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(given.getEmail(), actual.getEmail());
        Assertions.assertEquals(given.getPassword(), actual.getPassword());
        Assertions.assertEquals(given.getUserName(), actual.getUserName());

    }

    @Order(2)
    @Test
    public void updateUserTest(){
        User given = userTestDAO.findByEmail("test@example.com");
        given.setPassword("asdf");
        User actual = userTestDAO.updateUser(given);
        Assertions.assertEquals(given.getPassword(), actual.getPassword());
    }


    @Order(3)
    @ParameterizedTest
    @CsvSource({"test@example.com, i",
                    "text@example.com, e"})
    public void findByEmailAndCreateEntryTest(String email, String flag){
        User given = userTestDAO.findByEmail(email);
        Entry entry = new Entry();
        entry.setFlag(flag);
        entry.setUser(given);
        entry.setAmount(1);
        entry.setDescription("test");
        entry.setDate(LocalDate.now());
        Entry aEntry = entryTestDAO.createEntry(entry);
        Assertions.assertNotNull(aEntry.getId());

    }

    @Order(4)
    @Test
    public void createBudgetTest(){
        User given = userTestDAO.findByEmail("test@example.com");
        Budget budget = new Budget();
        budget.setAmount(1);
        budget.setDescription("test");
        budget.setUser(given);
        Budget aBudget = budgetTestDAO.createBudget(budget);
        Assertions.assertNotNull(aBudget.getId());
    }

    @Order(5)
    @Test
    public void updateBudgetTest(){
        User given = userTestDAO.findByEmail("test@example.com");
        Budget budget = budgetTestDAO.getByDescriptionAndUserId(given.getId(),"test");
        budget.setAmount(2);
        Budget aBudget = budgetTestDAO.updateBudget(budget);
        Assertions.assertEquals(budget.getAmount(), aBudget.getAmount());
    }

    @Order(6)
    @Test
    public void updateEntryTest(){
        User given = userTestDAO.findByEmail("test@example.com");
        Assertions.assertNotNull(given.getId(), "null");
        Entry entry = entryTestDAO.getENtryByUserIdAndDescriptionAndFlag(given.getId(),"test", "i");
        Assertions.assertNotNull(entry.getId(), "null");
        entry.setAmount(2);
        Entry aEntry = entryTestDAO.updateEntry(entry);
        Assertions.assertEquals(entry.getAmount(), aEntry.getAmount());
    }

    @Order(7)
    @Test
    public void getAllEntriesByUserTest(){
        User given = userTestDAO.findByEmail("test@example.com");
        List<Entry> entries = entryTestDAO.getAllEntriesByUserId(given.getId());
        boolean allTrue = entries.stream().allMatch(entry -> entry.getUserId().equals(given.getId()));
        Assertions.assertTrue(allTrue);
    }

    @Order(7)
    @Test
    public void deleteBudgetTest(){
        User given = userTestDAO.findByEmail("test@example.com");
        Budget budget = budgetTestDAO.getByDescriptionAndUserId(given.getId(),"test");
        budgetTestDAO.deleteBudget(budget);
        Assertions.assertNull(budgetTestDAO.getByDescriptionAndUserId(given.getId(),"test"));
    }

    @Order(8)
    @ParameterizedTest
    @CsvSource({"i","e"})
    public void deleteEntryTest( String flag){
        User given = userTestDAO.findByEmail("test@example.com");
        Entry entry = entryTestDAO.getENtryByUserIdAndDescriptionAndFlag(given.getId(),"test", flag);
        entryTestDAO.deleteEntry(entry);
        Assertions.assertNull(entryTestDAO.getENtryByUserIdAndDescriptionAndFlag(given.getId(),"test", flag));
    }

    @Order(9)
    @Test
    public void deleteUserTest(){
        User given = userTestDAO.findByEmail("test@example.com");
        userTestDAO.deleteUser(given);
        Assertions.assertNull(userTestDAO.findByEmail("test@example.com"));
    }
}
