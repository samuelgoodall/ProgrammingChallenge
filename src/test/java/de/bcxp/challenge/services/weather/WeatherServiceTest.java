package de.bcxp.challenge.services.weather;

import de.bcxp.challenge.util.dataprocessing.Mapper;
import de.bcxp.challenge.util.dataprocessing.Reader;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class WeatherServiceTest {

    private final Reader reader = mock(Reader.class);
    private final Mapper<String[], WeatherTO> mapper = mock(TestMapper.class);
    private final WeatherService underTest = new WeatherService(mapper, reader);
    private final String[] inputMaxBiggerMin = new String[]{"1", "90", "85"};
    private final String[] inputMaxMuchBiggerMin = new String[]{"2", "90", "5"};
    private final String[] inputMinBiggerMax = new String[]{"3", "9", "85"};
    private final String[] inputNegativeNumber = new String[]{"4", "-87", "-90"};
    private final String[] invalidInputValuesNotNumbers = new String[]{"5", "A", "B"};
    private final WeatherTO inputMaxBiggerMinTO = new WeatherTO(1, 90, 85);
    private final WeatherTO inputMaxMuchBiggerMinTO = new WeatherTO(2, 90, 5);
    private final WeatherTO inputMinBiggerMaxTO = new WeatherTO(3, 9, 85);
    private final WeatherTO inputNegativeNumberTO = new WeatherTO(4, -87, -90);
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

    /**
     * Test of the protected Method
     */

    @Test
    void getDayOfSmallestTemperatureSpread_ListOfWeatherItemsInStringArrFormInput_CorrectResult() {

        //Arrange
        List<String[]> input = new ArrayList<>();
        input.add(inputMaxBiggerMin);
        input.add(inputMaxMuchBiggerMin);

        when(mapper.mapFromTo(inputMaxBiggerMin)).thenReturn(inputMaxBiggerMinTO);
        when(mapper.mapFromTo(inputMaxMuchBiggerMin)).thenReturn(inputMaxMuchBiggerMinTO);

        int expected = 1;

        //Act
        int result = underTest.getDayOfSmallestTemperatureSpread(input);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getDayOfSmallestTemperatureSpread_ListOfWeatherItemsInStringArrFormInputOneEntryHasNegativeValues_CorrectResult() {

        //Arrange
        List<String[]> input = new ArrayList<>();
        input.add(inputMaxBiggerMin);
        input.add(inputMaxMuchBiggerMin);
        input.add(inputNegativeNumber);

        when(mapper.mapFromTo(inputMaxBiggerMin)).thenReturn(inputMaxBiggerMinTO);
        when(mapper.mapFromTo(inputMaxMuchBiggerMin)).thenReturn(inputMaxMuchBiggerMinTO);
        when(mapper.mapFromTo(inputNegativeNumber)).thenReturn(inputNegativeNumberTO);

        int expected = 4;

        //Act
        int result = underTest.getDayOfSmallestTemperatureSpread(input);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getDayOfSmallestTemperatureSpread_EmptyList_ThrowIllegalArgumentException() {

        //Arrange
        List<String[]> input = new ArrayList<>();

        //Act&Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> underTest.getDayOfSmallestTemperatureSpread(input));
        assertEquals("The list of weather items may not be empty!", exception.getMessage());
    }

    @Test
    void getDayOfSmallestTemperatureSpread_NullInput_ThrowIllegalArgumentException() {

        //Arrange
        List<String[]> input = null;

        //Act&Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> underTest.getDayOfSmallestTemperatureSpread(input));
        assertEquals("The list of weather items may not be null!", exception.getMessage());
    }

    @Test
    void getDayOfSmallestTemperatureSpread_ListOfWeatherItemsInStringArrFormInputOneItemMINBiggerMax_CorrectResultANDLogError() {

        //Arrange
        List<String[]> input = new ArrayList<>();
        input.add(inputMaxBiggerMin);
        input.add(inputMaxMuchBiggerMin);
        input.add(inputMinBiggerMax);

        when(mapper.mapFromTo(inputMaxBiggerMin)).thenReturn(inputMaxBiggerMinTO);
        when(mapper.mapFromTo(inputMaxMuchBiggerMin)).thenReturn(inputMaxMuchBiggerMinTO);
        when(mapper.mapFromTo(inputMinBiggerMax)).thenReturn(inputMinBiggerMaxTO);

        int expected = 1;

        //Act
        int result = underTest.getDayOfSmallestTemperatureSpread(input);

        //Assert
        assertEquals(expected, result);
        verify(logger).error("MinTemp bigger than MaxTemp in Data, data item with day:{}ignored", 3);
    }

    @Test
    void getDayOfSmallestTemperatureSpread_ListOfWeatherItemsInStringArrFormInputOneItemInvalidInput_CorrectResultANDLogError() {

        //Arrange
        List<String[]> input = new ArrayList<>();
        input.add(inputMaxBiggerMin);
        input.add(inputMaxMuchBiggerMin);
        input.add(invalidInputValuesNotNumbers);

        when(mapper.mapFromTo(inputMaxBiggerMin)).thenReturn(inputMaxBiggerMinTO);
        when(mapper.mapFromTo(inputMaxMuchBiggerMin)).thenReturn(inputMaxMuchBiggerMinTO);
        when(mapper.mapFromTo(invalidInputValuesNotNumbers)).thenThrow(NumberFormatException.class);


        int expected = 1;

        //Act
        int result = underTest.getDayOfSmallestTemperatureSpread(input);

        //Assert
        assertEquals(expected, result);
        verify(logger).error("Entry at position:{} has a non integer number in one of its values, day:{}, is ignored", 2, "5");
    }

    @Test
    void getDayOfSmallestTemperatureSpread_ListOfWeatherItemsInStringArrFormInputAllItemMINBiggerMax_ThrowIllegalArgumentExceptionANDLogErrors() {

        //Arrange
        List<String[]> input = new ArrayList<>();
        input.add(inputMinBiggerMax);

        when(mapper.mapFromTo(inputMinBiggerMax)).thenReturn(inputMinBiggerMaxTO);

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> underTest.getDayOfSmallestTemperatureSpread(input));

        //Assert
        verify(logger).error("MinTemp bigger than MaxTemp in Data, data item with day:{}ignored", 3);
        assertEquals("The list of weather items does not contain enough valid data!", exception.getMessage());
    }

    /**
     * Test of the public Method
     */
    @Test
    void getDayOfSmallestTemperatureSpread_ValidInputFile_CorrectResult() {

        //Arrange
        List<String[]> input = new ArrayList<>();
        input.add(inputMaxBiggerMin);
        Path filepath = mock(Path.class);

        int expected = 1;

        when(mapper.mapFromTo(inputMaxBiggerMin)).thenReturn(inputMaxBiggerMinTO);
        when(reader.readFile(filepath)).thenReturn(input);

        //Act
        int result = underTest.getDayOfSmallestTemperatureSpread(filepath);

        //Assert
        assertEquals(expected, result);
    }

    private interface TestMapper extends Mapper<String[], WeatherTO> {
    }

}
