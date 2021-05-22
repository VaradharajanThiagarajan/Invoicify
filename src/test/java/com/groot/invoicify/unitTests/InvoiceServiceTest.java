package com.groot.invoicify.unitTests;

import com.groot.invoicify.dto.DtoState;
import com.groot.invoicify.dto.InvoiceDto;
import com.groot.invoicify.dto.ItemDto;
import com.groot.invoicify.entity.Company;
import com.groot.invoicify.entity.Invoice;
import com.groot.invoicify.entity.Item;
import com.groot.invoicify.repository.CompanyRepository;
import com.groot.invoicify.repository.InvoiceRepository;
import com.groot.invoicify.repository.ItemRepository;
import com.groot.invoicify.service.InvoiceService;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * InvoiceServiceTest
 *
 */
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

	@Mock
	InvoiceRepository invoiceRepository;
	@Mock
	CompanyRepository companyRepository;
	@Mock
	ItemRepository itemRepository;

	@InjectMocks
	InvoiceService invoiceService;

	/**
	 *
	 */
	@Test
	public void createInvoice() {
		var itemsDto = List.of(
				new ItemDto("Description", 10, 14.50F, null)
		);
		var invoiceDto = new InvoiceDto("Test", "test", false, itemsDto);
		var company = new Company("Test", "Address1", "city1", "state1", "91367", "Mike", "CEO", "800-800-800");

		var invoiceEntity = new Invoice(1L, company, invoiceDto.getAuthor(), invoiceDto.getPaid(), List.of(),
				Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
		var expectedInvoiceDto = new InvoiceDto(1L, "Test", "test", false,
				List.of());
		var items = List.of(
				new Item("Description", 10, 14.50F, null, invoiceEntity)
		);
		var invoice = InvoiceService.MapToEntity(invoiceDto, company);

		when(companyRepository.findByName(invoiceDto.getCompanyName())).thenReturn(company);
		when(invoiceRepository.save(invoice)).thenReturn(invoiceEntity);

		var result = invoiceService.createInvoice(invoiceDto);

		verify(companyRepository, times(1)).findByName(invoiceDto.getCompanyName());
		verify(itemRepository, times(1)).saveAll(items);
		verify(invoiceRepository, times(1)).save(invoice);
		assertThat(result).isEqualTo(expectedInvoiceDto);
	}

	/**
	 *
	 */
	@Test
	public void deleteOneYearOlderAndPaidInvoices() {
		var invoices = List.of(
				new Invoice("authorName", true,
						Timestamp.valueOf(LocalDateTime.of(2000, 01, 01, 0, 0, 0, 0))),
				new Invoice("authorName", true,
						Timestamp.valueOf(LocalDateTime.of(1990, 01, 01, 0, 0, 0, 0))),
				new Invoice("authorName", false,
						Timestamp.valueOf(LocalDateTime.of(1990, 01, 01, 0, 0, 0, 0))),
				new Invoice("authorName", false,
						Timestamp.valueOf(LocalDateTime.now()))
		);

		var expectedResult = List.of(
				new InvoiceDto(),
				new InvoiceDto()
		);
		when(invoiceRepository.findAll()).thenReturn(invoices);
		var result = invoiceService.deletePaidAndOlderInvoices();

		verify(invoiceRepository, times(1)).delete(invoices.get(0));
		verify(invoiceRepository, times(1)).delete(invoices.get(1));
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	public void deleteInvoiceByIdTest() {
		Invoice invoice = new Invoice(1L, new Company("TCS"), "author", true, List.of(),
				Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

		when(invoiceRepository.findByInvoiceId(1L)).thenReturn(invoice);

		invoiceService.deletePaidInvoice(1L);

		verify(invoiceRepository).delete(invoice);

	}

	@Test
	public void deleteInvoiceByIdTest_givenUnpaidInvoice_shouldNotDeleteInvoice() {
		Invoice invoice = new Invoice(1L, new Company("TCS"), "author", false, List.of(),
				Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

		when(invoiceRepository.findByInvoiceId(1L)).thenReturn(invoice);

		invoiceService.deletePaidInvoice(1L);

		verify(invoiceRepository, times(0)).delete(invoice);

	}

	@Test
	public void deleteInvoiceByIdTest_givenNoFoundInvoice_shouldNotDeleteAnything() {

		when(invoiceRepository.findByInvoiceId(1L)).thenReturn(null);

		invoiceService.deletePaidInvoice(1L);

		verify(invoiceRepository, times(0)).delete(any());

	}


	/**
	 *
	 */
	@Test
	public void updateInvoiceTest() {
		var invoiceId = 1L;
		var invoiceDto = new InvoiceDto("Test1", "Author", true, List.of());

		var company = new Company("Test");
		var invoice = new Invoice(company, "Author", false);
		when(invoiceRepository.findById(invoiceId)).thenReturn(java.util.Optional.of(invoice));

		var newCompany = new Company(invoiceDto.getCompanyName());
		when(companyRepository.findByName(invoiceDto.getCompanyName())).thenReturn(newCompany);

		var mockInvoice = new Invoice(newCompany, invoiceDto.getAuthor(), invoiceDto.getPaid());
		mockInvoice.setInvoiceId(invoiceId);
		when(invoiceRepository.save(any())).thenReturn(mockInvoice);

		var result = invoiceService.updatedInvoice(invoiceId, invoiceDto);

		verify(invoiceRepository, times(1)).findById(invoiceId);
		verify(companyRepository, times(1)).findByName(invoiceDto.getCompanyName());
		verify(invoiceRepository, times(1)).save(any());
		var expectedInvoiceDto = new InvoiceDto(mockInvoice.getInvoiceId(),
				mockInvoice.getCompany().getName(),
				mockInvoice.getAuthor(),
				mockInvoice.getPaid(),
				List.of());
		assertEquals(expectedInvoiceDto, result);
	}

	/**
	 *
	 */
	@Test
	public void updateInvoice_noChangeToCompanyName() {
		var invoiceId = 1L;
		var invoiceDto = new InvoiceDto("Test", "Author", true, List.of());

		var company = new Company("Test");
		var invoice = new Invoice(company, "Author", false);
		when(invoiceRepository.findById(invoiceId)).thenReturn(java.util.Optional.of(invoice));

		var mockInvoice = new Invoice(company, invoiceDto.getAuthor(), invoiceDto.getPaid());
		mockInvoice.setInvoiceId(invoiceId);
		when(invoiceRepository.save(any())).thenReturn(mockInvoice);

		var result = invoiceService.updatedInvoice(invoiceId, invoiceDto);

		verify(invoiceRepository, times(1)).findById(invoiceId);
		verify(companyRepository, times(0)).findByName(invoiceDto.getCompanyName());
		verify(invoiceRepository, times(1)).save(any());
		var expectedInvoiceDto = new InvoiceDto(mockInvoice.getInvoiceId(),
				mockInvoice.getCompany().getName(),
				mockInvoice.getAuthor(),
				mockInvoice.getPaid(),
				List.of());
		assertEquals(expectedInvoiceDto, result);
	}

	/**
	 *
	 */
	@Test
	public void updateInvoice_modifiedItem() {
		ItemDto itemDto = new ItemDto("Description", 10, 14.50F, null);
		Item itemEntity = new Item("Description", 10, 14.50F, null);

		var invoiceId = 1L;
		var invoiceDto = new InvoiceDto("Test", "Author", true, List.of(itemDto));

		var company = new Company("Test");
		var invoice = new Invoice(company, "Author", false);
		when(invoiceRepository.findById(invoiceId)).thenReturn(java.util.Optional.of(invoice));

		var updatedInvoice = new Invoice(company, invoiceDto.getAuthor(), invoiceDto.getPaid());
		var mockInvoice = new Invoice(company, invoiceDto.getAuthor(), invoiceDto.getPaid());
		mockInvoice.setInvoiceId(invoiceId);
		itemEntity.setInvoice(updatedInvoice);
		when(invoiceRepository.save(any())).thenReturn(mockInvoice);

		when(itemRepository.save(new Item("Description", 10, 14.50F, null, invoice))).thenReturn(itemEntity);

		var result = invoiceService.updatedInvoice(invoiceId, invoiceDto);

		verify(invoiceRepository, times(1)).findById(invoiceId);
		verify(companyRepository, times(0)).findByName(invoiceDto.getCompanyName());
		verify(invoiceRepository, times(1)).save(any());
		var expectedInvoiceDto = new InvoiceDto(mockInvoice.getInvoiceId(),
				mockInvoice.getCompany().getName(),
				mockInvoice.getAuthor(),
				mockInvoice.getPaid(),
				List.of(new ItemDto(null, "Description", 10, 14.50F, null)));
		assertEquals(expectedInvoiceDto, result);
	}

	/**
	 *
	 */
	@Test
	public void updateInvoice_deleteItem() {
		ItemDto itemDto = new ItemDto(1L, "Description", 10, 14.50F, null);
		itemDto.setState(DtoState.Deleted);
		Item itemEntity = new Item(1L, "Description", 10, 14.50F, null);

		var invoiceId = 1L;
		var invoiceDto = new InvoiceDto("Test", "Author", true, List.of(itemDto));

		var company = new Company("Test");
		var invoice = new Invoice(company, "Author", false);
		when(invoiceRepository.findById(invoiceId)).thenReturn(java.util.Optional.of(invoice));

		var updatedInvoice = new Invoice(company, invoiceDto.getAuthor(), invoiceDto.getPaid());
		var mockInvoice = new Invoice(company, invoiceDto.getAuthor(), invoiceDto.getPaid());
		mockInvoice.setInvoiceId(invoiceId);
		itemEntity.setInvoice(updatedInvoice);
		when(invoiceRepository.save(any())).thenReturn(mockInvoice);

		when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(itemEntity));

		var result = invoiceService.updatedInvoice(invoiceId, invoiceDto);

		verify(invoiceRepository, times(1)).findById(invoiceId);
		verify(companyRepository, times(0)).findByName(invoiceDto.getCompanyName());
		verify(invoiceRepository, times(1)).save(any());
		verify(itemRepository, times(1)).delete(itemEntity);
		var expectedInvoiceDto = new InvoiceDto(mockInvoice.getInvoiceId(),
				mockInvoice.getCompany().getName(),
				mockInvoice.getAuthor(),
				mockInvoice.getPaid(),
				List.of());
		assertEquals(expectedInvoiceDto, result);
	}

	/**
	 *
	 */
	@Test
	public void isInvoicePaidReturnTrueTest() {
		var invoiceId = 1L;
		var company = new Company("Test");
		var invoice = new Invoice(company, "Author", true);
		when(invoiceRepository.findById(invoiceId)).thenReturn(java.util.Optional.of(invoice));

		var result = invoiceService.isInvoicePaid(invoiceId);

		verify(invoiceRepository, times(1)).findById(invoiceId);
		assertThat(result).isTrue();
	}

	/**
	 *
	 */
	@Test
	public void isInvoicePaidReturnFalse() {
		var invoiceId = 1L;
		var company = new Company("Test");
		var invoice = new Invoice(company, "Author", false);
		when(invoiceRepository.findById(invoiceId)).thenReturn(java.util.Optional.of(invoice));

		var result = invoiceService.isInvoicePaid(invoiceId);

		verify(invoiceRepository, times(1)).findById(invoiceId);
		assertThat(result).isFalse();
	}

	/**
	 *
	 */
	@Test
	public void isInvoicePaidReturnFalseWhenInvoiceNotFound() {
		var invoiceId = 1L;
		var result = invoiceService.isInvoicePaid(invoiceId);
		verify(invoiceRepository, times(1)).findById(invoiceId);
		assertThat(result).isFalse();
	}

	/**
	 *
	 */
	@Test
	public void fetchAllInvoiceTest() {
		var pageNo = 0;
		var paging = PageRequest.of(pageNo, 10, Sort.by("createDt"));
		var company = new Company("Test");
		var invoice = new Invoice(company, "Author", false);
		var invoices = new PageImpl(List.of(invoice));
		when(invoiceRepository.findAll(paging)).thenReturn(invoices);

		var invoiceDtoList = this.invoiceService.fetchAllInvoices(pageNo);

		verify(invoiceRepository, times(1)).findAll(paging);
		assertThat(invoiceDtoList).isEqualTo(List.of(new InvoiceDto(invoice.getInvoiceId(),
				invoice.getCompany().getName(),
				invoice.getAuthor(),
				invoice.getPaid(),
				List.of())));
	}
}
