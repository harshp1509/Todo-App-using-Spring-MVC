package todoapp;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.Todo;

@Controller
public class homecontroller {
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/home")
	public String first(Model m){
		String str="home";
		List<Todo> list=(List<Todo>)context.getAttribute("list");
		
		m.addAttribute("todos",list);
		m.addAttribute("page","home");
		return "Home";
	}
	
	@RequestMapping("/add")
	public String addTodo(Model m) {
		Todo t=new Todo();
		m.addAttribute("page", "add");
		m.addAttribute("todo",t);
		return "Home";
	}
	
	@RequestMapping(value="/saveTodo",method=RequestMethod.POST)
	public String saveTodo(@ModelAttribute("todo") Todo t, Model m) {
		System.out.println(t);
		t.setTodoDate(new Date());
		
		List<Todo> list=(List<Todo>)context.getAttribute("list");
		list.add(t);
		m.addAttribute("msg","successfully added..");
		return "Home";
	}
}

