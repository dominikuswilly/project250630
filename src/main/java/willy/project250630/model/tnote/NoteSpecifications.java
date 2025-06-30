package willy.project250630.model.tnote;

import org.springframework.data.jpa.domain.Specification;

public class NoteSpecifications {
    public static Specification<TNote> hasTitleLike(String title){
        return (root, query, builder) -> {
            if(title == null || title.isEmpty()){
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.get("title")), "%"+title.toLowerCase()+"%");
        };
    }

    public static Specification<TNote> hasContentLike(String content){
        return (root, query, builder) -> {
            if(content == null || content.isEmpty()){
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.get("content")), "%"+content.toLowerCase()+"%");
        };
    }
}
