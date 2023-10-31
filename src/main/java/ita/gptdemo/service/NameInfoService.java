package ita.gptdemo.service;

import ita.gptdemo.dto.AgeResponse;
import ita.gptdemo.dto.GenderResponse;
import ita.gptdemo.dto.NameInfoResponse;
import ita.gptdemo.dto.NationalityResponse;
import ita.gptdemo.entity.NameInfo;
import ita.gptdemo.repository.INameInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NameInfoService {

    INameInfoRepository nameInfoRepository;

    public NameInfoService(INameInfoRepository nameInfoRepository) {
        this.nameInfoRepository = nameInfoRepository;
    }

    private WebClient webClient = WebClient.create();

    public Mono<NameInfoResponse> getNameInfo(String name) {


        NameInfoResponse response = new NameInfoResponse();

        // Læs fra databasen hvis det eksisterer
        if(nameInfoRepository.findByName(name).isPresent()){

            NameInfo nameInfo = nameInfoRepository.findByName(name).get();
            response.setName(nameInfo.getName() + "xxx");
            response.setGender(nameInfo.getGender());
            response.setGenderProbability(nameInfo.getGenderProbability());
            response.setAge(nameInfo.getAge());
            response.setAgeCount(nameInfo.getAgeCount());
            response.setCountry(nameInfo.getCountry()); // assuming the first one is the most probable
            response.setCountryProbability(nameInfo.getCountryProbability());

            return Mono.just(response);
        }


        Mono<GenderResponse> gender = webClient.get()
                .uri("https://api.genderize.io/?name=" + name)
                .retrieve()
                .bodyToMono(GenderResponse.class);

        Mono<AgeResponse> age = webClient.get()
                .uri("https://api.agify.io/?name=" + name)
                .retrieve()
                .bodyToMono(AgeResponse.class);

        Mono<NationalityResponse> nationality = webClient.get()
                .uri("https://api.nationalize.io/?name=" + name)
                .retrieve()
                .bodyToMono(NationalityResponse.class);

        return Mono.zip(gender, age, nationality).map(t -> {
            GenderResponse genderResp = t.getT1();
            AgeResponse ageResp = t.getT2();
            NationalityResponse nationalityResp = t.getT3();

            // Hvad der skal returneres til controller
           // NameInfoResponse response = new NameInfoResponse();
            response.setName(name);
            response.setGender(genderResp.getGender());
            response.setGenderProbability(genderResp.getProbability());
            response.setAge(ageResp.getAge());
            response.setAgeCount(ageResp.getCount());
            response.setCountry(nationalityResp.getCountry().get(0).getCountry_id()); // assuming the first one is the most probable
            response.setCountryProbability(nationalityResp.getCountry().get(0).getProbability());

            NameInfo nameInfo = new NameInfo(response.getName(), response.getGender(), response.getGenderProbability(), response.getAge(), response.getAgeCount(), response.getCountry(), response.getCountryProbability());
            nameInfoRepository.save(nameInfo);

            return response;
        });
    }
}

