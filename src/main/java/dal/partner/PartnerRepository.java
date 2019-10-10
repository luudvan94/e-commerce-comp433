package dal.partner;

import dal.Repository;
import domain.partner.Partner;

public interface PartnerRepository extends Repository<Partner, String> {

	Partner login(String username, String password);
	
	boolean isUserAlreadyExist(String username);
}
