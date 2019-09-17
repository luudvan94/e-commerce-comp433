package repository;

import entity.CardProfile;

public interface CardProfileRepository extends Repository<CardProfile, String> {

	CardProfile cardProfileByCustomerID(String id);
}
