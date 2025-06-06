package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import kr.re.bgp.jpademo.dto.BaseDto;
import kr.re.bgp.jpademo.dto.ResponseDto;
import kr.re.bgp.jpademo.dto.account.AccountCreateDto;
import kr.re.bgp.jpademo.dto.account.AccountResponseDto;
import kr.re.bgp.jpademo.entity.Account;
import kr.re.bgp.jpademo.exception.UserAlreadyExistException;
import kr.re.bgp.jpademo.repository.AccountRepository;
import kr.re.bgp.jpademo.service.auth.RoleEnum;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends CrudService<Account, AccountResponseDto> {
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
        if (isExistUser(dto)) {
            throw new UserAlreadyExistException();
        }

        Account account = new Account()
                .withEmail(dto.getEmail())
                .withPassword(passwordEncoder.encode(dto.getPassword()))
                .withRole(RoleEnum.USER);

        accountRepository.save(account);
    }

    private boolean isExistUser(AccountCreateDto dto) {
        return accountRepository.findByEmail(dto.getEmail()).isPresent();
    }
}
