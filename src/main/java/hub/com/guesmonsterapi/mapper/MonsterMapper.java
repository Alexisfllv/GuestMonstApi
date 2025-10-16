package hub.com.guesmonsterapi.mapper;


import hub.com.guesmonsterapi.dto.Monster.MonstruoDTORequest;
import hub.com.guesmonsterapi.dto.Monster.MonstruoDTOResponse;
import hub.com.guesmonsterapi.dto.Monster.MonstruoDTOResponseQuest;
import hub.com.guesmonsterapi.model.Monster;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonsterMapper {


    Monster toMonster(MonstruoDTORequest monstruoDTORequest);

    MonstruoDTOResponse toMonstruoDTOResponse(Monster monster);

    MonstruoDTOResponseQuest toMonstruoDTOResponseQuest(Monster monster);

}
