package org.helianto.mailer

import com.iservport.message.domain.{ContactData, Message, MessageData, MessageDefaults}

/**
  * Sample message.
  */
object SampleMessage {

  val logo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAYAAACOEfKtAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAABvlAAAb5QH6Tx+ZAAAAB3RJTUUH3wMTFjUh3EOL0QAACf5JREFUeNrtnH+MVFcVxz+zwMIWhPJDWvmhBSk/KitYaaBttt3SijS2YpsCV6RZZFmKJTVgmlxwaIyUQa5NCjEURVhwoo0PgqQVjVRbWCUxjY2InaVlU6Qi0lZEfnUpy/4a/3jnlcu4w877tVQyJ9nMvLf33XvO951z7rnnnjtQpCIV6f+YEl09oDYOAEYr77oH0BP4FFAhf2OBQcDH5bF/AyeBQ8A++TsKXDRatXTU7zUJoDaODdzdwFIBazhwnc/uPgCOCahrjVa/zx3jWgRwMDAb+C7QP+ffjcB/gFPAGeCcfAcYAPQFrpfvA4E+Oc+fBr4DbDNanbgmAPS0QRunO7AZ+BrQPafZj4BNokltQKvRqi1Pf93k+W6iuTXAopxmrcDzwAKjVWvcGpnoAq17HEiJ9nj0E9GU3R2Ztx9XINfTRbPnWc3OAEmj1YY45SuJa5LQxumrjXMYeE7AawF2ACVGq68Du+3n8oFXXpX6n3tGq9z7u6XPEhmjRcZ8ThvnsDZOX5u3j6wGWib7CFArfguZNWuMVg1BHX15VWqk+L0jmXSysZMJaoy4hQr59zmg2mi1I2qTTsSggTXAj+WyGVjqx4zKq1Jk0knKq1LDgJni40Z30HQTsCWTTr5qP9eB+1gLlMqthUarTR85DbQ0bwPwDbn9HlButDpZaIxmgbdGQpzSTobOAoeByTILk0knL4sJtXEGARngRnnmh0arx6PSxESE4CWBVXJ7P/AFo9WpQhktr0olJKDeCdzvk42zwEOZdHKvrYkWbwOA3wG3SvsVRqtUFCAmIgLvMQlHAA4AnwuyKiivSu0CHgjIzgXgpkw6eSLfxAb8BZgo3xcZrTaGBTEKDRwvJgJQD3zeaNXst5/yqtQPgCdCsnMauDGTTjbn4bUU+DMw3hvWaFUfZsDuIcOVfsCfLDO6W0IIv+D1y4nhglJ/YAnw/Tz/bxEej3i8a+N8AjgbVAtLwpgusA4ok9tTjFanxLH7peXAxyKaGL+ZL34EssLjFLkuA9aJG+paE9bGmQq8IperjFZPBfUn5VWpJplAoqJxmXTyUCd++2lghdy+12i1p0sAlDfVA3gDGAU0Ga3KgkpaXpUaI+vgKGldJp1cWoAsF4BeEgrdArT4VQDfJiwDVAh4WOYQlG6IYYk6rsB2Hu+jgIog1hN0LbzdivfqQ64xr48BwKEFWlK9yGDLFN8sLIM+KPk4gJX5Uk8+6GQMAP6tQEtq08ZZCbwADNTGeRDY5UcTSwKY70y5fMdo9WIEGY5jMQB4wEc08SLwjtyaGbsPBB6Wz+8FWW3kUiadPMalzHNUtN6HQnwoiyVbPABq41QCvSUgfSnC/Nq3IwTvYCadPJknDsznll4SmXqLjLFp4GPy2QQcjSKbIYLWAlHtYzzTUWqrEy08KjLZMkYLoDZOApgul/uCrHfzmDCZdLIVd6MpLL2ZSSfTAUKzZtykL8B0kTVyDRzEpfzctigdlmjMBlkaBqUTXrqqUO3LIU+mUpE1cgAHyAoE620RlRbK51Jga4Au3gNGZNLJphBseDL1EFljAbC7qPzbEaTB7O99tHFu0cYZnEkn559vbrkDOF5gVyuAkZl08oNCJ448Zvy2FRvHAmCZrJ3fjwi4Ido4T2vjnJI+DwL/0sbJzpo4dmH1lAkV7dnsLNxM8luiZSeBv8vqYRUwMJNOpnCTqUFN16b3RcaC1/Z+ViLeDtv5MOBJJmQRsCFfMiML89491/joA58ZlTZaTSuvSpXibqYnkM33TDrZZvnPqLzJeUmr9Y0DwH5hARTwlgLPFtC8GzBfG2eI0er+fKmyCMGzZesXhwl7nfcMoYHTCgTPpunaOMu7qGCop18l8QPgGfnsE8LvBfXyq7VxPh1HZUEO9cmRNRoAhfEmW739CCOmOxSYFEK4uXFpoSWLZ7pNhcpXUigAuDterUBCG2dAAGGeDCnn1LjUzto7ToiMpwuVz48Jn5LOAe4MwOetIeUcGrP5ejK1+skO+QXQ27KcEYDBviEFLIsZQE+mllgANFpd5FL6+ytBFvohBYy76tSTab/IGnkcCLARd2O6jzbOcOCYD1/4U+CrIQR8LcYJZLg1A2+MJZ0lWvhzK16a5GciMVr9BrcOOij9Mq4JRKKDnjkyRgugNa17laXL/IYzwJqAch4wWv0qxvBlmS2bH5kSAQasxi0YBxhntDrks483cQvE/UxeQ3E38OMw4bGWf14A1PoZJ0hlQingOdmXgWlGq6yP58tw9yAqCgGvvb39tmeWzzli30zveJXm5kZq5twXFrwE8FvA66in30x7kMqEZi6VoVXgs7LAaHXBaHUXkAT+eYVl4/brepcNGztiyKRap+71WqcuW+vUXax16ra1tjaNq5lzH7Xb6sIq4A3Wi3wiyDZF0PK2LcBTwGDRwvF+/Y7RarU2zrPAENGA0bj7s/sSUD/+5mEXLza37gPusB4vBWYBM2u31T1cPbvyhZAAviyTxwmRyTeFqc6y01KPGq1+FmUFfK1Tt1Je0pVofCLBwfmzK335cVm6zZXQCuBbRqu1XQagxcTrQLkkGvpFtVO3dfuefu3tJYVkRDZVq8qFAf34WdzKrIzR6rNBX34gE7YGmolbmtYLt8hoHNAWVgvb20smF9h0st8Xj5uorReePRno0gpVC8gGa0K5GdgQkQn3jrid/eI3CK/exNEQhtHQR72MVuutuHChNs7WAAH2ZZSFQnf9jviZuIQ3z+Q3C+9cVQDFd9QAu+TWPG2c9WHqjheoygO45Rad0S98+Ov1XCpk32W0qokiwx2FBnpAzbAW/Iu1cXZ6PjYQo4nsnVy54v+talW5sYBlWnfhZbGVlJgRVcQQyWlNoxWyGrmdS5UFDwEN2jij/Wpj7bY9VM++5zhuCe67HTR5PpFgQgFaNxpoEF4Q3m43WmWjCreiPu7aZrSab4E4UkBc4s16hQBZPXsqW5y9VKvK/dWqcgjwZdyN9CezMKJaVc6dP7vywhW0rpeM2SA8AGwV3tqiFDi2A9dSZ7fXutUIKKPVr8P2vdmpY4GqzDfulwCHy3cP7zFa1cUhZyIm8OyTkutwj/p7lME9hL3daHXabh9kDPneX5Z4iyWw/9DUgSXeidE4sjld9ZsJEyUDMyjHbeyUHOEbuGeLOz2nYZ1TKcU927GMy0tz23FraL5otDpwLfxmggdiQhIDq4G7cpqdB/6B+6sdDbJSOIWcAcY9AzdAkhZjcE8JfLKDQPoPuOXCfzRaZbviJ1C6/Id3BNShojn3AjcRfMftAm611ivAGqPV8a6W5aoAaAHZQ8xxAvCI5OZu6+Sx13CLIXcAfxWzb7laMlxVAPNNIJIt6W3NpI3A+dxsT1f/SlGRilSkIhWpSEUqUpGKVKQiFalIRSpSkYpUpCIVKQT9F1JpA0hf2M9AAAAAAElFTkSuQmCC"

  val content = new Message()
    .sender(new ContactData("mauricio@iservport.com", "Mauricio").entityName("SENDER"))
    .recipient(new ContactData("mauricio@iservport.com", "Mauricio").entityName("RECIPIENT"))
    .apiHome("APIHOME")
    .servicePath("SERVICE")
    .token("TOKEN")
    .contextLogo(logo)
    .defaults(
      new MessageDefaults()
        .greeting("HELLO")
        .seeOnline("SEE")
        .fallBack("FALLBACK")
        .sentByText("SENTBY")
        .disclaimer("DISCLAIMER")
        .unsubscribeText("UNSBS1")
        .unsubscribeCaption("UNSBS2")
        .unsubscribeService("UNSBS3")
        .ensure("ENSURE")
        .copyright("CPRGHT")
    )
    .messageData(
      new MessageData()
        .subject("SUBJECT")
        .title("TITLE")
        .procedure("PROCEDURE")
        .callToAction("ACTION")
        .trailingInfo("ETC")
    )


}
