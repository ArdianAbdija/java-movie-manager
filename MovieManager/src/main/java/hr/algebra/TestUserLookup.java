package hr.algebra;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;

public class TestUserLookup {

    public static void main(String[] args) throws Exception {
        Repository repo = RepositoryFactory.getRepository();
        System.out.println(repo.selectUserByUsername("admin"));
    }
}
