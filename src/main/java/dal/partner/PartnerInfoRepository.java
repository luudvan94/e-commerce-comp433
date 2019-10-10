package dal.partner;

import dal.Repository;
import domain.partner.PartnerInfo;

public interface PartnerInfoRepository extends Repository<PartnerInfo, String> {
	
	PartnerInfo partnerInfobyPartnerID(String id);

}
