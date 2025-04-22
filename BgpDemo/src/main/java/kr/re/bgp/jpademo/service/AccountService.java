package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import kr.re.bgp.jpademo.dto.BaseDto;
import kr.re.bgp.jpademo.dto.ResponseDto;
import kr.re.bgp.jpademo.dto.account.AccountCreateDto;
import kr.re.bgp.jpademo.dto.account.AccountResponseDto;
import kr.re.bgp.jpademo.entity.Account;
import kr.re.bgp.jpademo.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, AccountResponseDto> {
    private final AccountRepository accountRepository;

    protected AccountService(EntityManager entityManager,
                             ModelMapper modelMapper,
                             AccountRepository accountRepository) {
        super(entityManager, modelMapper);
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseDto create(BaseDto dto) {
        return convertToDto(accountRepository.save(mapsObjToClass(dto, Account.class)));
    }

    private ResponseDto convertToDto(Account account) {
        return mapsObjToClass(account, AccountResponseDto.class);
    }

    @Override
    public ResponseDto update(BaseDto dto) {
        return null;
    }

    @Override
    public ResponseDto retrieve(Long id) {
        return null;
    }

    public void signUp(AccountCreateDto dto) {

    }
}
