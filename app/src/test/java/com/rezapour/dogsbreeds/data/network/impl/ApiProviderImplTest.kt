package com.rezapour.dogsbreeds.data.network.impl

import com.google.common.io.Resources
import com.google.common.truth.Truth.assertThat
import com.rezapour.dogsbreeds.data.exception.DataProviderException
import com.rezapour.dogsbreeds.data.network.ApiProvider
import com.rezapour.dogsbreeds.data.network.retrofit.ApiService
import com.rezapour.dogsbreeds.data.network.retrofit.client.RetrofitClient
import com.rezapour.dogsbreeds.util.MainCoroutineRule
import com.rezapour.dogsbreeds.util.sampels.NetworkSampleFactory
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.io.InputStream
import java.net.HttpURLConnection
import com.rezapour.dogsbreeds.R

class ApiProviderImplTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    lateinit var mockWebServer: MockWebServer
    lateinit var apiProvider: ApiProvider


    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        val apiService =
            RetrofitClient().retrofitProvider(mockWebServer.url("/"), 1000)
                .create(ApiService::class.java)
        apiProvider = ApiProviderImpl(apiService)
    }

    private fun responseMapper(fileName: String): String {
        val inputStreamUser: InputStream =
            File(Resources.getResource(fileName).toURI()).inputStream()
        return inputStreamUser.bufferedReader().use { it.readText() }
    }

    @Test
    fun `getBreeds response list of Assets when run successfully`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(responseMapper("BreedsRespond.json"))

        mockWebServer.enqueue(responseTest)

        runTest {
            val response = apiProvider.getBreeds()
            assertThat(response).isEqualTo(NetworkSampleFactory.getBreedNetworkEntity())
        }
    }

    @Test
    fun `getBreeds throws internet connection exception when api call is failed`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                apiProvider.getBreeds()
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }

    @Test
    fun `getBreeds throws access denied when response code is 400 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)

        mockWebServer.enqueue(responseTest)


        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                apiProvider.getBreeds()
            }
        }.messageId
        assertEquals(R.string.error_access_denied, messageId)
    }

    @Test
    fun `getBreeds throws server error when response code is 500 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                apiProvider.getBreeds()
            }
        }.messageId
        assertEquals(R.string.error_server_error, messageId)
    }

    @Test
    fun `getBreeds throws internet connection problem when response code is unknown range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_MOVED_PERM)

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                apiProvider.getBreeds()
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }


    @Test
    fun `getBreedsImages response list of Assets when run successfully`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(responseMapper("BreedsImageRespond.json"))

        mockWebServer.enqueue(responseTest)

        runTest {
            val response = apiProvider.getBreedsImages("")
            assertThat(response).isEqualTo(NetworkSampleFactory.getBreedsImageNetworkEntity())
        }
    }

    @Test
    fun `getBreedsImages throws internet connection exception when api call is failed`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                apiProvider.getBreedsImages("")
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }

    @Test
    fun `getBreedsImages throws access denied when response code is 400 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)

        mockWebServer.enqueue(responseTest)


        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                apiProvider.getBreedsImages("")
            }
        }.messageId
        assertEquals(R.string.error_access_denied, messageId)
    }

    @Test
    fun `getBreedsImages throws server error when response code is 500 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                apiProvider.getBreedsImages("")
            }
        }.messageId
        assertEquals(R.string.error_server_error, messageId)
    }

    @Test
    fun `getBreedsImages throws internet connection problem when response code is unknown range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_MOVED_PERM)

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runTest {
                apiProvider.getBreedsImages("")
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }


}