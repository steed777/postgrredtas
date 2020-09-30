package com.github.steed777.postgrredtask.postgrredtas.components;



import com.github.steed777.postgrredtask.postgrredtas.model.Employee;
import com.github.steed777.postgrredtask.postgrredtas.repository.EmployeeRepositiry;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent//для помещения класса в spring контекст для внедрения зависимости
@UIScope
public class EmployeeEditor extends VerticalLayout implements KeyNotifier {
    //KeyNotifier позволяет отслеживать нажатие клавиш
private final EmployeeRepositiry employeeRepositiry;
private Employee employee;

    private TextField lastname = new TextField("","Фамилия");
    private TextField firstname = new TextField("","Имя");
    private TextField patronymic = new TextField("","Отчество");
    private TextField departament = new TextField("","Отдел");

    private Button save = new Button("Сохранить", VaadinIcon.CHECK.create());
    private Button cancel = new Button("Отменить");
    private Button delete = new Button("Удалить", VaadinIcon.TRASH.create());
    private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private Binder<Employee> employeeBinder = new Binder<>(Employee.class);

    private ChangeHandler changeHandler;

    public interface ChangeHandler{
     void onChange();
    }
@Autowired
    public EmployeeEditor(EmployeeRepositiry employeeRepositiry) {
        this.employeeRepositiry = employeeRepositiry;

        add(lastname, firstname, patronymic, departament, actions);
        employeeBinder.bindInstanceFields(this);//привязка формы
//к филдам
    setSpacing(true);//добавляет интервалы между филдами

    save.getElement().getThemeList().add("primary");//getElement().getThemeList() берет тему
    delete.getElement().getThemeList().add("error");//добавляет оформление: "error"-кнопка
//становится красного цвета...
    addKeyPressListener(Key.ENTER, e -> save());//при нажатии на enter отправляет
//в метод save
    save.addClickListener(e -> save());//устанавливает обработчики кнопок
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editEmployee(employee));
    setVisible(false);//делает форму невидимой
    }
    private void save() {
        employeeRepositiry.save(employee);//с помощью репозитория сохранять сотрудника
        changeHandler.onChange();
    }
    private void delete() {
        employeeRepositiry.delete(employee);
        changeHandler.onChange();
    }
    public void editEmployee(Employee emp) {
        if (emp == null) {
            setVisible(false);
            return;
        }if (emp.getId() != null) {
            this.employee = employeeRepositiry.findById(emp.getId()).orElse(emp);
        }else {
            employee = emp;
        }
        employeeBinder.setBean(this.employee);//ляжет в филды
        setVisible(true);

        lastname.focus();//видимости
    }

    public void setChangeHandler(ChangeHandler changeHandler) {
        this.changeHandler = changeHandler;
    }
}
