package repository;

import entity.Partner;

public interface PartnerRepository extends Repository<Partner, String> {

	Partner login(String username, String password);
	
	boolean isUserAlreadyExist(String username);
}
