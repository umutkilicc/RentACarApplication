package com.RentACar.RentACar.service.impl;

import com.RentACar.RentACar.common.service.impl.BaseService;
import com.RentACar.RentACar.dto.CommentDto;
import com.RentACar.RentACar.entity.Comment;
import com.RentACar.RentACar.repository.CommentRepository;
import com.RentACar.RentACar.service.CommentService;
import com.RentACar.RentACar.service.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl extends BaseService<CommentRepository, CommentMapper, Comment, CommentDto> implements CommentService {

    private final CommentRepository commentRepository;

    protected CommentServiceImpl(CommentMapper mapper, CommentRepository repository, CommentRepository commentRepository) {
        super(mapper, repository);
        this.commentRepository = commentRepository;
    }

    /**
     * @param commentDto
     * @return
     */
    public CommentDto saveComment(CommentDto commentDto) {

        return super.save(commentDto);
    }

    public List<CommentDto> saveAllComments(List<CommentDto> CommentDtoList) {

        return super.saveAll(CommentDtoList);
    }

    /**
     * @return
     */
    @Override
    public List<CommentDto> findAll() {

        return super.findAll();
    }

    /**
     * @param uuid
     * @return
     */
    @Override
    public CommentDto findById(UUID uuid) {

        return super.findById(uuid);
    }

    /**
     * @param carId
     * @return
     */
    @Override
    public List<CommentDto> findByCarId(UUID carId) {

        return mapper.entityListToDtoList(commentRepository.findByCarIdOrderByCreateDateDesc(carId));
    }

    /**
     * @param CommentDto
     */
    @Override
    public void delete(CommentDto CommentDto) {

        super.delete(CommentDto);
    }

    /**
     * @param uuid
     */
    @Override
    public void deleteById(UUID uuid) {

        super.deleteById(uuid);
    }

    /**
     * @param CommentDtos
     */
    @Override
    public void deleteAll(List<CommentDto> CommentDtos) {

        super.deleteAll(CommentDtos);
    }

    @Override
    public String loadComments(UUID carId) {

        StringBuilder commentsString = new StringBuilder();

        List<Comment> comments = commentRepository.findByCarIdOrderByCreateDateDesc(carId);

        for (Comment comment : comments) {
            commentsString.append(commentHTML(comment));
            commentsString.append(loadReplies(carId, 0));
        }

//        comments.stream().map(comment -> {
//            commentsString.append(commentHTML(comment, 0));
//            commentsString.append(loadReplies(carId, 0));
//            return comment;
//        });

        return commentsString.toString()
                .replaceAll("\\r\\n", "<br>"); // handle new lines
    }

    private StringBuilder loadReplies(UUID carId, int marginLeft) {

        StringBuilder replyString = new StringBuilder();

        List<Comment> comments = commentRepository.findByCarIdOrderByCreateDateDesc(carId);

//        marginLeft = carId == 0 ? 0 : marginLeft + 50;

        for (Comment comment : comments) {
            replyString.append(commentHTML(comment));
            replyString.append(loadReplies(carId, marginLeft));
        }

        return replyString;
    }

    private String commentHTML(Comment comment) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");

        String date = simpleDateFormat.format(comment.getCreateDate());

        return "<div class=\"comments-div\" id=\"comments\" " + ":" + "\">\n" +
                "                <div id=\"comment-id-" + comment.getId() + "\">\n" +
                "                    <a class=\"reply\" onclick=\"return reply(" + comment.getId() + ")\">Reply</a>\n" +
                "                    <p><span class=\"author-name\" id=\"author-name\">" + comment.getCreatedUser() + "</span> <i>on " + date + "</i></p>\n" +
                "                    <p id=\"posted-comment\">" + comment.getComment() + "</p>\n" +
                "                </div>    \n" +
                "            </div>";

    }
}
