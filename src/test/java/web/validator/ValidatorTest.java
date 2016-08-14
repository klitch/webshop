package web.validator;

import entity.FormBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {
    @Mock
    private FormBean formBean;

    private SignUpFormValidator validator = new SignUpFormValidator();
     
    @Before
    public void init(){
	when(formBean.getFirstName()).thenReturn("FirstName");
	when(formBean.getLastName()).thenReturn("LastName");
	when(formBean.getEmail()).thenReturn("vetal2@gmail.com");
	when(formBean.getPassword()).thenReturn("alalalalLLa99");
	when(formBean.getValidCaptcha()).thenReturn("alala");
	when(formBean.getCaptcha()).thenReturn("alala");
    }

    @Test
    public void testValidateNameValid() {	
	when(formBean.getFirstName()).thenReturn("First");
	assertTrue(validator.validate(formBean).size() == 0);
    }
    
    @Test
    public void testValidateNameInvalid() {	
	when(formBean.getFirstName()).thenReturn("53543");
	assertTrue(validator.validate(formBean).size() != 0);
    }
    
    @Test
    public void testValidateLastNameValid() {	
	when(formBean.getLastName()).thenReturn("Last");
	assertTrue(validator.validate(formBean).size() == 0);
    }
    
    @Test
    public void testValidateLastNameInvalid() {	
	when(formBean.getFirstName()).thenReturn("53543");
	assertTrue(validator.validate(formBean).size() != 0);
    }
    
    @Test
    public void testValidateEmailValid() {	
	when(formBean.getEmail()).thenReturn("vetaldom@mail.ru");
	assertTrue(validator.validate(formBean).size() == 0);
    }
    
    @Test
    public void testValidateEmailInvalid() {	
	when(formBean.getEmail()).thenReturn("vaas@mail");
	assertTrue(validator.validate(formBean).size() != 0);
    }
    
    @Test
    public void testValidatePassValid() {	
	when(formBean.getPassword()).thenReturn("adasdasdD29");
	assertTrue(validator.validate(formBean).size() == 0);
    }
    
    @Test
    public void testValidatePassInvalid() {	
	when(formBean.getPassword()).thenReturn("oloo");
	assertTrue(validator.validate(formBean).size() != 0);
    }

}
