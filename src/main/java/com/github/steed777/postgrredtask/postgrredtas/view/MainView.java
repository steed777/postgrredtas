package com.github.steed777.postgrredtask.postgrredtas.view;



import com.github.steed777.postgrredtask.postgrredtas.components.EmployeeEditor;
import com.github.steed777.postgrredtask.postgrredtas.model.Employee;
import com.github.steed777.postgrredtask.postgrredtas.repository.EmployeeRepositiry;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")// для отбражения страницы с объектами
public class MainView extends VerticalLayout { //
    private final EmployeeRepositiry employeeRepositiry;
    private final Grid<Employee> employeeGrid;

    private final TextField filter;
    private final Button addNewBtn;
    private final EmployeeEditor employeeEditor;

    @Autowired
    public MainView(EmployeeRepositiry employeeRepositiry, EmployeeEditor employeeEditor){

        this.employeeRepositiry = employeeRepositiry;
        this.employeeGrid = new Grid<>(Employee.class);// для реализации таблицы Employee.class
        this.employeeEditor = employeeEditor;
        this.filter = new TextField();
        this.addNewBtn = new Button("Новый сотрудник", VaadinIcon.PLUS.create());

        HorizontalLayout action = new HorizontalLayout(filter, addNewBtn);


        filter.setPlaceholder("Поиск");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listEmployees(e.getValue()));

        add(action, employeeGrid, employeeEditor);

        employeeGrid.asSingleSelect().addValueChangeListener(e -> {
            employeeEditor.editEmployee(e.getValue());
        });

        addNewBtn.addClickListener(e -> employeeEditor.editEmployee(new Employee()));

        employeeEditor.setChangeHandler(() -> {
            employeeEditor.setVisible(false);
            listEmployees(filter.getValue());
        });
        listEmployees("");
    }
    private void listEmployees(String name){
if(name.isEmpty()){
    employeeGrid.setItems(this.employeeRepositiry.findAll());//для отображения базы данных
}else {//в grid подставляем то что возвращет репос методом findAll()
    employeeGrid.setItems(this.employeeRepositiry.findByName(name));
}//если форма фильтра пуста-выводится все таблица, если нет то выводятся данные с совпадение
    }// введенных сиволов, благодаря hql запросу из репоситория
}
