import java.util.ArrayList;
import java.util.List;

public class CompanyDao implements Dao<Company>{
    private List<Company> companies = new ArrayList<>();

    public CompanyDao(){

    }

    public Company get(long id) {
        for (int i = 0; i < companies.size(); i++){
            if (companies.get(i).getId() == id)
                return companies.get(i);
        }
        return null;
    }

    @Override
    public List<Company> getAll() {
        return companies;
    }

    @Override
    public void save(Company company) {
        companies.add(company);
    }

    @Override
    public void update(Company company, String[] params) {
      company.setName(params[0]);
      company.setAddress(new Address(params[1], params[2], params[3], params[4]));
    }

    @Override
    public void delete(Company company) {
        companies.remove(company);
    }
}
