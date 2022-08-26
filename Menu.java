import java.util.Scanner;

public class Menu{
  public static Scanner input = new Scanner(System.in);
  public static StudentDao studentCrud = new StudentDao();
  public static CompanyDao companyCrud = new CompanyDao();
  public static VacancyDao vacancyCrud = new VacancyDao();

  public void screenTitle(){
    clearScreen(); 

    Archive arq = new Archive();

    /*StudentDao studentCrud = new StudentDao();
    CompanyDao companyCrud = new CompanyDao();
    VacancyDao vacancyCrud = new VacancyDao();*/
    StudentDao studentCrudTemp = new StudentDao();

    try {
      companyCrud = arq.readCompanies();
      studentCrudTemp = arq.readStudents();
      vacancyCrud = arq.readVacancy(studentCrudTemp);
      studentCrud = arq.readStudents(vacancyCrud);
    }
    catch(Exception e){
      System.out.println("Erro na leitura!");
    }
    
    System.out.println( "Bem Vindo, a Procura de estagio\n");
    System.out.println( "\nDeseja entrar como?\n1.Estudante \n2.Empresa\n");

    int n = input.nextInt();
    
    switch(n){
      case 1:

        clearScreen();
        
        System.out.println("Deseja: \n1.Entrar\n2.Cadastrar\n");

        menuStudentLogin();
        
        clearScreen();

        break;
      case 2:

        clearScreen();
        
        System.out.println("Deseja: \n1.Entrar \n2.Cadastrar\n");

        menuCompanyLogin();

        clearScreen();
        
        break;
        
    }

    clearScreen();

    try {
      arq.writeCompanies(companyCrud);
      arq.writeVacancy(vacancyCrud);
      arq.writeStudent(studentCrud);
    }
    catch(Exception a){
      System.out.println("Erro na escrita");
    }
    System.out.println("\n Obrigado por escolher a Procura de Estagio.");
    
    input.close();
  }

  public void menuStudentLogin(){
    int n = input.nextInt();
    
    switch(n){
      case 1:

        //Procurar estudante na studentCrud e Logar
        
        Student student = getStudent();

        if(student == null){
          break;
        }
        clearScreen();

        // Pra testar essa parte é preciso que a parte dos arquivos esteja pronta
        // pra recuperar um estudante que ja exista
        
        System.out.printf( "Bem Vindo, %s\n", student.getName());
        
        menuStudentLoged(student);
        
        break;
      case 2:

        //Cadastrar, cria e adiciona a lista
        
        Student studen = createStudent();
        
        studentCrud.save(studen);
        
        clearScreen();

        System.out.print("\nCadastrado com sucesso!\n");
        System.out.printf("\nSeu ID para logar novamente é %d!\n", studen.getId());
        
        menuStudentLoged(studen);
        
        break;
    }
    
    input.close();
  }

  public void menuStudentLoged(Student student){
    System.out.println( "\nDeseja \n1.Listar vagas disponiveis \n2.Aplicar a uma Vaga\n3.Excluir uma Vaga(Aparece as vagas a quais se aplicou)\n4.Excluir sua conta\n5.Sair\n");
    
    int n = input.nextInt();
    Vacancy vacancy;

    switch(n){
      case 1:
        //Funcao de Listar Vagas
        vacancyCrud.listToStringScreen();
        
        menuStudentLoged(student);
        break;
      case 2:
        //Aplicar a uma Vaga
        vacancy = vacancyCrud.get(search());
        if(student.containsVacancy(vacancy)){
          System.out.println("Você já se aplicou a essa Vaga.\n");
        }
        else{
          try{
            vacancy.addInterested(student);
            student.addVacancy(vacancy);
          } catch(MaxCapacityException e){
            System.out.println("Essa vaga atingiu o maximo de inscrições.");
          }
        }
        menuStudentLoged(student);
        break;
      case 3:
        //Funcao Listar minhas vagas e excluir

        clearScreen();
        if(student.myVacanciesListToScreen()){
          Vacancy vacancyToDelete = vacancyCrud.get(search());
          
          vacancyToDelete.removeInterested(student.getId());
          student.removeVacancy(vacancyToDelete);
          
          System.out.println("Removida a aplicação à vaga!\n");
        }
        else{
          System.out.println("Você não possui nenhuma aplicação à vagas!\n");
        }

        menuStudentLoged(student);
        break;
      case 4:
        //Funcao Excluir Conta, excluir estudante em Vagas

        System.out.println("Deseja excluir sua conta?\n1.Sim\n2.Não\n");
        int choice = input.nextInt();
        if(choice == 1){
          vacancyCrud.removeStudent(student);
          studentCrud.delete(student);
          System.out.println("Conta excluida!\n");
        }
        else{
          System.out.println("Operação cancelada!\n");
          break;
        }
        break;
      case 5:
        break;
        
    }
    
  }

  public Student getStudent(){
    int option;
    Student student = studentCrud.get(search());
    
    while(student == null){
        System.out.println("\nEstudante não encontrado.\nDeseja\n1.Tentar novamente.\n2.Sair\n");

        option = input.nextInt();

        if(option == 2){
          break;
        }
      
        student = studentCrud.get(search());
    }
    return student;
    
  }

  public void clearScreen(){
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }

  public Student createStudent(){
    input.nextLine();
    
    System.out.print("Insira o seu nome:");

    String name = input.nextLine();

    System.out.print("Insira a sua universidade:");

    String university = input.nextLine();

    System.out.print("Insira seu curso:");

    String course = input.nextLine();
    
    Student s = new Student(name, university, course);
    
    return s;
  }

  public void menuCompanyLogin(){
    int n = input.nextInt();
    
    switch(n){
      case 1:
        //Verificar a Empresa
        Company company = getCompany();

        if(company == null){
          break;
        }
        
        clearScreen();
        
        System.out.printf( "Bem Vindo, %s\n", company.getName());

        menuCompanyLoged(company);
        break;
      case 2:
        //Cadastrar empresa
        Company compan = createCompany();

        companyCrud.save(compan);

        System.out.print("\nCadastrado com sucesso!\n");
        System.out.printf("\nSeu ID para logar novamente é %d!\n", compan.getId());

        menuCompanyLoged(compan);
        break;
    }
  }

  public Company createCompany(){
    input.nextLine();
    
    System.out.print("Insira o nome da Empresa:");

    String name = input.nextLine();

    System.out.print("Insira o Endereco:");
    
    System.out.print("Insira o Bairro:");

    String district = input.nextLine();

    System.out.print("Insira a Cidade:");

    String city = input.nextLine();

    System.out.print("Insira o Estado:");

    String state = input.nextLine();

    System.out.print("Insira o País:");

    String country = input.nextLine();

    Address adres = new Address(district, city, state, country);

    Company company = new Company(name, adres);
    
    return company;
  }

  public void menuCompanyLoged(Company company){

    System.out.println( "\nDeseja \n1.Criar uma Vaga \n2.Listar suas vagas \n3.Ver aplicações em uma vaga\n4.Editar uma Vaga\n5.Excluir vaga\n6.Sair\n");

    int n = input.nextInt();

    Vacancy vacancy = new Vacancy();

    switch(n){
      case 1:
        //Criar Vaga
        Vacancy vacancie = createVacancy();

        //Salvando na lista de empresa e no CrudVacancy
        company.addVacancy(vacancie);
        vacancyCrud.save(vacancie);

        clearScreen();

        System.out.println("\nVaga criada com sucesso!\n");

        menuCompanyLoged(company);
        break;
      case 2:
        //Funcao Listar Vagas da Empresa
        clearScreen();
        
        company.vacanciesListToScreen();
        
        menuCompanyLoged(company);
        break;
      case 3:
        //Ver alunos interessados na vaga
        Vacancy vacan = vacancyCrud.get(search());
        vacan.toStringInterested();
        //

        menuCompanyLoged(company);
        break;
      case 4:
        //Funcao Editar Vaga
        editVacancy(company);
        System.out.println("\nVaga editada com sucesso!\n");
        //
        
        menuCompanyLoged(company);
        break;
      case 5:
        //Excluir Vaga
        vacancy = vacancyCrud.get(search());

        //Cada estudante verifica se tem a Vaga com mesmo ID nele para removela
        for(Student student:studentCrud.getAll()){
          student.removeVacancy(vacancy.getId());
        }
        //Remove da empresa e da VacancyCrud
        vacancyCrud.delete(vacancy);
        company.removeVacancy(vacancy.getId());
        System.out.println("\nVaga excluida com sucesso!\n");
        //
        
        menuCompanyLoged(company);
        break;
      case 6:
        break;
        
    }
  }

  public Vacancy createVacancy(){

    input.nextLine();

    System.out.print("Insira o nome da Vaga:");

    String name = input.nextLine();

    System.out.print("Insira a Descricao:");

    String description = input.nextLine();

    System.out.print("Insira a capacidade maxima de aplicações(-1 para ilimitada):");

    int maxCapacity = input.nextInt();

    System.out.print("Insira o valor do salario:");

    double salary = input.nextDouble();

    Vacancy vacancy = new Vacancy(name, description, maxCapacity, salary);

    return vacancy;
  }

  public Company getCompany(){
    int option;
    Company company = companyCrud.get(search());
    
    while(company == null){
        System.out.println("\nEmpresa não encontrado.\nDeseja\n1.Tentar novamente.\n2.Sair\n");

        option = input.nextInt();

        if(option == 2){
          break;
        }
      
        company = companyCrud.get(search());
    }
    return company;
  }

  public long search(){
    System.out.print("Insira o ID:");
    
    return input.nextLong();
  }

  public void editVacancy(Company company){

    Vacancy vacancy = vacancyCrud.get(search());

    company.removeVacancy(vacancy.getId());

    input.nextLine();

    System.out.print("Insira a nova Descrição:");
    String description = input.nextLine();

    System.out.print("Insira a nova capacidade maxima de aplicações(-1 para ilimitada):");
    int maxCapacity = input.nextInt();

    System.out.print("Insira o novo valor do salário:");
    double salary = input.nextDouble();

    vacancy.setDescription(description);
    vacancy.setMaxCapacity(maxCapacity);
    vacancy.setSalary(salary);

    company.addVacancy(vacancy);

    clearScreen();
  }

}