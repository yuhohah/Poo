import java.io.*;

public class Archive{

    public CompanyDao readCompanies() throws Exception {
        String file = "Company.txt";

        BufferedReader br = new BufferedReader(new FileReader(file));

        CompanyDao companyCrud = new CompanyDao();
        String string;
        String[] arrayCompany;
        String[] arrayVacancies;

        //Lendo arquivo Company
        while ((string = br.readLine()) != null) {
          arrayCompany = string.split(";");
          arrayVacancies = arrayCompany[6].split(",");

          //Cria Endereco e Empresa
          Company company = new Company(Long.parseLong(arrayCompany[0]), arrayCompany[1], arrayCompany[2], arrayCompany[3], arrayCompany[4], arrayCompany[5]);

          //Verifica o contador global
          if(company.getId() > IdGenerator.getCounter()){
            IdGenerator.setCounter(company.getId());
          }

          //Le os arquivos para adicionar as listas em Empresa -> Vaga -> Interessados
          //TODO: testar
          StudentDao studentCrud = readStudents();
          VacancyDao vacancyCrud = readVacancy(studentCrud);

          //Adiciona a Vaga a Empresa
          for(String sting: arrayVacancies){
            company.addVacancy(vacancyCrud.get(Long.parseLong(sting)));
          }

          //Salva no Crud de Empresa
            companyCrud.save(company);
        }

        br.close();
      
        return companyCrud;
    }

    public void writeCompanies(CompanyDao companyCrud) throws Exception{
      String file = "Company.txt";

      BufferedWriter bw = new BufferedWriter(new FileWriter(file));

      for(Company company: companyCrud.getAll()) {
          bw.write(company.toStringWrite() + "\n");
      }

      bw.close();
    }

    public VacancyDao readVacancy(StudentDao studentCrud) throws Exception{
        String file = "Vacancy.txt";

        BufferedReader br = new BufferedReader(new FileReader(file));

        VacancyDao vacancyCrud = new VacancyDao();
        String s1;
        String[] arrayVacancy;
        String[] arrayStudents = new String[10];

        //Lendo arquivo Vacancy
        while ((s1 = br.readLine()) != null) {
            arrayVacancy = s1.split(";");
          if(arrayVacancy.length == 6){
            arrayStudents = arrayVacancy[5].split(",");
          }

            Vacancy vac = new Vacancy(Long.parseLong(arrayVacancy[0]), arrayVacancy[1], arrayVacancy[2], Integer.parseInt(arrayVacancy[3]), Double.parseDouble(arrayVacancy[4]));

          if(vac.getId() > IdGenerator.getCounter()){
            IdGenerator.setCounter(vac.getId());
          }

            //Pesquisa o estudante pelo ID e adiciona a lista
          if(arrayVacancy.length == 6){
            for (String sting : arrayStudents) {
                vac.addInterestedRead(studentCrud.get(Long.parseLong(sting)));
            }
          }

        vacancyCrud.save(vac);
        }

      br.close();
      return vacancyCrud;
    }

    public void writeVacancy(VacancyDao vacancyCrud) throws Exception{
      String file = "Vacancy.txt";

      BufferedWriter bw = new BufferedWriter(new FileWriter(file));

      for(Vacancy vacancy: vacancyCrud.getAll()){

        bw.write(vacancy.toStringWrite() + "\n");

      }

      bw.close();
    }

    public StudentDao readStudents() throws Exception{
        String file = "Student.txt";

        BufferedReader br = new BufferedReader(new FileReader(file));

        StudentDao studentCrud = new StudentDao();
        String s;
        String[] arrayStudent;

        while((s = br.readLine()) != null){
            arrayStudent = s.split(";");

            Student student = new Student(Long.parseLong(arrayStudent[0]), arrayStudent[1], arrayStudent[2], arrayStudent[3]);
          
            studentCrud.save(student);

          if(student.getId() > IdGenerator.getCounter()){
            IdGenerator.setCounter(student.getId());
          }
        }

        br.close();

        return studentCrud;
    }

    public StudentDao readStudents(VacancyDao vacancyCrud) throws Exception{
        String file = "Student.txt";

        BufferedReader br = new BufferedReader(new FileReader(file));

        StudentDao studentCrud = new StudentDao();
        String s;
        String[] arrayStudent;
        String[] arrayVacancies = new String[10];

      //Leitura do arquivo Student
        while((s = br.readLine()) != null){
            arrayStudent = s.split(";");
          if(arrayStudent.length == 5){
            arrayVacancies = arrayStudent[4].split(",");
          }

            Student student = new Student(Long.parseLong(arrayStudent[0]), arrayStudent[1], arrayStudent[2], arrayStudent[3]);

          //Verificando se o contador está correto
          if(student.getId() > IdGenerator.getCounter()){
            IdGenerator.setCounter(student.getId());
          }

          //Adicionando a Vaga a lista de vagas do estudante
          if(arrayStudent.length == 5){
            for(String string: arrayVacancies){
                student.addVacancy(vacancyCrud.get(Long.parseLong(string)));
            }
          }
          
          studentCrud.save(student);
        }

        br.close();
        return studentCrud;
    }

    public void writeStudent(StudentDao studentCrud) throws Exception{
        String file = "Student.txt";

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        for(Student student: studentCrud.getAll()){

            bw.write(student.toStringWrite() + "\n");

        }

        bw.close();
    }

}