package com.smoothstack.lms.librarianservice.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smoothstack.lms.librarianservice.LibrarianEntityFactory;
import com.smoothstack.lms.librarianservice.entity.BookCopy;
import com.smoothstack.lms.librarianservice.entity.BookCopy.BookCopyId;
import com.smoothstack.lms.librarianservice.entity.LibraryBranch;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({ "classpath:schema.sql", "classpath:data.sql" })
public class LibrarianFullSystemIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSuccessfullCreateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 7l;
        Long branchId = 2l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(bookId, branchId, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/lms/librarian/book-copies")
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.book.id").value(bookId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.branch.id").value(branchId));
    }

    @Test
    public void testNullBookCopyIdCreateBookCopy() throws JsonProcessingException, Exception {
        BookCopyId bookCopyId = new BookCopyId();
        Long amount = 1l;
        BookCopy bookCopy = new BookCopy();

        bookCopy.setId(bookCopyId);
        bookCopy.setAmount(amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/lms/librarian/book-copies")
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testNullBranchIdCreateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 7l;
        Long branchId = null;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(bookId, branchId, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/lms/librarian/book-copies")
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testNullBookIdCreateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = null;
        Long branchId = 2l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(bookId, branchId, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/lms/librarian/book-copies")
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testNullAmountCreateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 7l;
        Long branchId = 2l;
        Long amount = null;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(bookId, branchId, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/lms/librarian/book-copies")
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testInvalidBookIdCreateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 100l;
        Long branchId = 2l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(bookId, branchId, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/lms/librarian/book-copies")
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testInvalidBranchIdCreateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 7l;
        Long branchId = 100l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(bookId, branchId, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/lms/librarian/book-copies")
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testInvalidBookCopyIdCreateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 1l;
        Long branchId = 1l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(bookId, branchId, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/lms/librarian/book-copies")
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    public void testSuccessfulGetBooksNotInBookCopies() throws Exception {
        Long branchId = 1l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/books/book-copies/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());
    }

    @Test
    public void testEmptyResultGetBooksNotInBookCopies() throws Exception {
        Long branchId = 3l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/books/book-copies/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isEmpty());
    }

    @Test
    public void testZeroBranchIdGetBooksNotInBookCopies() throws Exception {
        Long branchId = 0l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/books/book-copies/branches/{branchId}", branchId))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testNegativeBranchIdGetBooksNotInBookCopies() throws Exception {
        Long branchId = -1l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/books/book-copies/branches/{branchId}", branchId))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSuccessfullGetLibraryBranches() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/lms/librarian/branches").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());
    }

    @Test
    public void testSuccessfullGetLibraryBranchById() throws Exception {
        Long branchId = 1l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(branchId));
    }

    @Test
    public void testZeroBranchIdGetLibraryBranchById() throws Exception {
        Long branchId = 0l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testNegativeBranchIdGetLibraryBranchById() throws Exception {
        Long branchId = -1l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSuccessfullGetBookCopies() throws Exception {
        Long branchId = 1l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/book-copies/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());
    }

    @Test
    public void testZeroBranchIdGetBookCopies() throws Exception {
        Long branchId = 0l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/book-copies/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testNegativeBranchIdGetBookCopies() throws Exception {
        Long branchId = -1l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/book-copies/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSuccessfullUpdateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 1l;
        Long branchId = 1l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(null, null, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/lms/librarian/book-copies/books/{bookId}/branches/{branchId}", bookId, branchId)
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.book.id").value(bookId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.branch.id").value(branchId));
    }

    @Test
    public void testNullAmountUpdateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 7l;
        Long branchId = 2l;
        Long amount = null;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(null, null, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/lms/librarian/book-copies/books/{bookId}/branches/{branchId}", bookId, branchId)
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testInvalidBookIdUpdateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 100l;
        Long branchId = 2l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(null, null, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/lms/librarian/book-copies/books/{bookId}/branches/{branchId}", bookId, branchId)
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testInvalidBranchIdUpdateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 7l;
        Long branchId = 100l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(null, null, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/lms/librarian/book-copies/books/{bookId}/branches/{branchId}", bookId, branchId)
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testInvalidBookCopyIdUpdateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 2l;
        Long branchId = 1l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(null, null, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/lms/librarian/book-copies/books/{bookId}/branches/{branchId}", bookId, branchId)
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSuccessfullUpdateLibraryBranch() throws JsonProcessingException, Exception {
        Long branchId = 1l;
        String name = "name";
        String address = "address";
        LibraryBranch branch = LibrarianEntityFactory.createLibraryBranch(null, name, address);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/lms/librarian/branches/{branchId}", branchId)
                        .content(this.objectMapper.writeValueAsString(branch)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(branchId));
    }

    @Test
    public void testNullNameUpdateLibraryBranch() throws JsonProcessingException, Exception {
        Long branchId = 1l;
        String name = null;
        String address = "address";
        LibraryBranch branch = LibrarianEntityFactory.createLibraryBranch(null, name, address);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/lms/librarian/branches/{branchId}", branchId)
                        .content(this.objectMapper.writeValueAsString(branch)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testNullAddressUpdateLibraryBranch() throws JsonProcessingException, Exception {
        Long branchId = 1l;
        String name = "name";
        String address = null;
        LibraryBranch branch = LibrarianEntityFactory.createLibraryBranch(null, name, address);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/lms/librarian/branches/{branchId}", branchId)
                        .content(this.objectMapper.writeValueAsString(branch)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testInvalidBranchIdUpdateLibraryBranch() throws JsonProcessingException, Exception {
        Long branchId = 100l;
        String name = "name";
        String address = "address";
        LibraryBranch branch = LibrarianEntityFactory.createLibraryBranch(null, name, address);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/lms/librarian/branches/{branchId}", branchId)
                        .content(this.objectMapper.writeValueAsString(branch)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSuccessfullRemoveBookCopy() throws Exception {
        Long bookId = 1l;
        Long branchId = 1l;

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/lms/librarian//book-copies/books/{bookId}/branches/{branchId}",
                        bookId, branchId))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testInvalidBookCopyIdRemoveBookCopy() throws Exception {
        Long bookId = 2l;
        Long branchId = 1l;

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/lms/librarian//book-copies/books/{bookId}/branches/{branchId}",
                        bookId, branchId))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
