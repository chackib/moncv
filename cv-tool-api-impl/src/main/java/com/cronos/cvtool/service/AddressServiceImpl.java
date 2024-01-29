package com.cronos.cvtool.service;

import org.springframework.stereotype.Service;

import com.cronos.cvtool.entity.candidate.Address;
import com.cronos.cvtool.entity.candidate.Country;
import com.cronos.cvtool.repository.AddressRepository;
import com.cronos.cvtool.repository.CountryRepository;
@Service
public class AddressServiceImpl implements AddressService{
	private AddressRepository addressRepository;

	private CountryRepository countryRepository;

	public AddressServiceImpl(AddressRepository addressRepository, CountryRepository countryRepository){
		this.addressRepository = addressRepository;
		this.countryRepository = countryRepository;
	}

	@Override
	public Address saveAddress(Address address) {
		Country country = countryRepository.getCountryByCode(address.getCountry().getCode());
		address.setCountry(country);
		return addressRepository.save(address);
	}

}
