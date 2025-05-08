package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import kr.re.bgp.jpademo.dto.BaseDto;
import kr.re.bgp.jpademo.dto.ResponseDto;
import kr.re.bgp.jpademo.dto.account.AccountCreateDto;
import kr.re.bgp.jpademo.dto.account.AccountResponseDto;
import kr.re.bgp.jpademo.entity.Account;
import kr.re.bgp.jpademo.exception.UserAlreadyExistException;
import kr.re.bgp.jpademo.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, AccountResponseDto> {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    protected AccountService(EntityManager entityManager,
                             ModelMapper modelMapper,
                             AccountRepository accountRepository,
                             PasswordEncoder passwordEncoder) {
        super(entityManager, modelMapper);
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
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
        if (accountRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(encodedPassword);
        accountRepository.save(account);
    }
}
