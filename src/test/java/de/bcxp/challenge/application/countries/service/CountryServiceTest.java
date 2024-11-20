package de.bcxp.challenge.application.countries.service;

import de.bcxp.challenge.application.countries.controller.CountryPopDensityTO;
import de.bcxp.challenge.application.weather.service.WeatherService;
import de.bcxp.challenge.util.dataprocessing.Mapper;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CountryServiceTest {

    private final Mapper<String[], CountryTO> mapper = mock(CountryServiceTest.TestMapper.class);
    private final CountryService underTest = new CountryService(mapper);
    private final String[] input = new String[]{"Austria", "Vienna", "1995", "8926000", "83855", "447718", "0.922", "19"};
    private final String[] input2 = new String[]{"Kirgistan", "Vienna", "1995", "9926000", "83855", "447718", "0.922", "19"};

    private final String[] inputNegativePopulation = new String[]{"Kirgistan2", "Vienna", "1995", "-8926000", "83855", "447718", "0.922", "19"};
    private final String[] inputNegativeArea = new String[]{"Kirgistan3", "Vienna", "1995", "8926000", "-83855", "447718", "0.922", "19"};

    private final String[] inputExtraDotsAndCommas = new String[]{"Kirgistan4", "Vienna", "1995", "4.036.355,00", "83855", "447718", "0.922", "19"};
    private final String[] invalidInputValuesNotNumbers = new String[]{"Kirgistan5", "Vienna", "1995", "ab", "-vf", "447718", "0.922", "19"};

    private final CountryTO inputTO = new CountryTO(input[0], Double.parseDouble(input[3]), Double.parseDouble(input[4]));
    private final CountryTO input2TO = new CountryTO(input2[0], Double.parseDouble(input2[3]), Double.parseDouble(input2[4]));

    private final CountryTO inputExtraDotsAndCommasTO = new CountryTO(inputExtraDotsAndCommas[0], Double.parseDouble(inputExtraDotsAndCommas[3].replaceAll("\\.", "").replaceAll(",", ".")), Double.parseDouble(inputExtraDotsAndCommas[4]));

    private final Logger logger = mock(Logger.class);

    /**
     * The field of the WeatherService that holds the logger
     * using reflection to access it
     */
    private final Field loggerField = ReflectionUtils
            .findFields(WeatherService.class, f -> f.getName().equals("logger"),
                    ReflectionUtils.HierarchyTraversalMode.TOP_DOWN)
            .getFirst();

    /**
     * Used to replace the logger with a mocked one
     * in order to verify that error messages are logged
     *
     * @throws IllegalAccessException when something goes wrong during
     *                                access to the logger field
     */
    @BeforeEach
    void setUp() throws IllegalAccessException {
        loggerField.setAccessible(true);
        loggerField.set(underTest, logger);
    }

    @Test
    void getPopulationDensityOfInputs_CorrectInputs_ReturnCorrectResult() {
        //Arrange
        List<String[]> inputs = new ArrayList<>();
        inputs.add(input);
        inputs.add(input2);

        when(mapper.mapFromTo(input)).thenReturn(inputTO);
        when(mapper.mapFromTo(input2)).thenReturn(input2TO);

        //Act
        CountryPopDensityTO countryPopDensityTO = underTest.getPopulationDensityOfInputs(inputs);

        //Assert
        assertEquals("Kirgistan", countryPopDensityTO.country());
        assertEquals(118.3709975553038, countryPopDensityTO.populationDensity());
    }

    @Test
    void getPopulationDensityOfInputs_InputWithExtraDotsAndComma_ReturnCorrectResult() {
        //Arrange
        List<String[]> inputs = new ArrayList<>();
        inputs.add(input);
        inputs.add(inputExtraDotsAndCommas);

        when(mapper.mapFromTo(input)).thenReturn(inputTO);
        when(mapper.mapFromTo(inputExtraDotsAndCommas)).thenReturn(inputExtraDotsAndCommasTO);

        //Act
        CountryPopDensityTO result = underTest.getPopulationDensityOfInputs(inputs);

        //Assert
        assertEquals("Austria", result.country());
        assertEquals(106.44565022956293, result.populationDensity());
    }

    @Test
    void getPopulationDensityOfInputs_InputsThatHaveNegativeArea_ReturnCorrectResultANDLogErrors() {
        //Arrange
        List<String[]> inputs = new ArrayList<>();
        inputs.add(input);
        inputs.add(inputNegativeArea);

        when(mapper.mapFromTo(input)).thenReturn(inputTO);
        when(mapper.mapFromTo(inputNegativeArea)).thenThrow(new IllegalArgumentException("Area cannot be negative!"));


        //Act
        CountryPopDensityTO countryPopDensityTO = underTest.getPopulationDensityOfInputs(inputs);

        //Assert
        assertEquals("Austria", countryPopDensityTO.country());
        assertEquals(106.44565022956293, countryPopDensityTO.populationDensity());
        verify(logger).error("Area cannot be negative!");
    }

    @Test
    void getPopulationDensityOfInputs_InputsThatHaveInvalidArea_ReturnCorrectResultANDLogErrors() {
        //Arrange
        List<String[]> inputs = new ArrayList<>();
        inputs.add(input);
        inputs.add(invalidInputValuesNotNumbers);

        when(mapper.mapFromTo(input)).thenReturn(inputTO);
        when(mapper.mapFromTo(invalidInputValuesNotNumbers)).thenThrow(new NumberFormatException("Area is not a valid double!"));


        //Act
        CountryPopDensityTO countryPopDensityTO = underTest.getPopulationDensityOfInputs(inputs);

        //Assert
        assertEquals("Austria", countryPopDensityTO.country());
        assertEquals(106.44565022956293, countryPopDensityTO.populationDensity());
        verify(logger).error("Area is not a valid double!");
    }

    @Test
    void getPopulationDensityOfInputs_InputsThatAreNegativePopulation_ReturnCorrectResultANDLogErrors() {
        //Arrange
        List<String[]> inputs = new ArrayList<>();
        inputs.add(input);
        inputs.add(inputNegativePopulation);

        when(mapper.mapFromTo(input)).thenReturn(inputTO);
        when(mapper.mapFromTo(inputNegativePopulation)).thenThrow(new IllegalArgumentException("Population cannot be negative!"));


        //Act
        CountryPopDensityTO countryPopDensityTO = underTest.getPopulationDensityOfInputs(inputs);

        //Assert
        assertEquals("Austria", countryPopDensityTO.country());
        assertEquals(106.44565022956293, countryPopDensityTO.populationDensity());
        verify(logger).error("Population cannot be negative!");
    }

    @Test
    void getPopulationDensityOfInputs_OnlyInvalidInputsNegativePopulation_ThrowIllegalArgumentExceptionANDLogErrors() {
        //Arrange
        List<String[]> inputs = new ArrayList<>();
        inputs.add(inputNegativeArea);
        inputs.add(inputNegativePopulation);

        when(mapper.mapFromTo(inputNegativeArea)).thenThrow(new IllegalArgumentException("Area cannot be negative!"));
        when(mapper.mapFromTo(inputNegativePopulation)).thenThrow(new IllegalArgumentException("Population cannot be negative!"));


        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> underTest.getPopulationDensityOfInputs(inputs));

        //Assert
        verify(logger).error("Population cannot be negative!");
        verify(logger).error("Area cannot be negative!");
        assertEquals("The list of country items does not contain enough valid data!", exception.getMessage());
    }


    /**
     * Needed for avoiding unchecked assignment exception with mockito mock
     */
    private interface TestMapper extends Mapper<String[], CountryTO> {
    }
}
